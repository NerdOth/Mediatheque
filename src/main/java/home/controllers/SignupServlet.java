/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Membre;
import home.SessionBeans.MembreFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author machd
 */
public class SignupServlet extends HttpServlet {
    
    @EJB
    private MembreFacade membrefacade;
    
    private Membre membre;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("nom") != null && request.getParameter("prenom") != null && request.getParameter("email") != null && request.getParameter("motdepasse") != null) {

            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String motdepasse = request.getParameter("motdepasse");

            
            membre = new Membre();
            
            membre.setIdMembre(1);
            membre.setNom(nom);
            membre.setPrenom(prenom);
            membre.setEmail(email);
            membre.setMotPasse(motdepasse);
            membre.setFonction("Membre");
            
            membrefacade.create(membre);
            
            request.setAttribute("membreNom", nom);
            
            this.getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);
        }
    }

}
