/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Categorie;
import home.Beans.Membre;
import home.SessionBeans.CategorieFacade;
import home.SessionBeans.MembreFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author machd
 */
public class ModifierMembreServlet extends HttpServlet {

    @EJB
    private MembreFacade membreFacade;

    @EJB
    private CategorieFacade categoriefacade;

    private List<Categorie> listcategorie;

    private List<Membre> listmembre;

    private Membre membre;

    private int idMembre;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("idMembre") != null) {

            idMembre = Integer.parseInt(request.getParameter("idMembre"));

            membre = membreFacade.find(idMembre);

            listcategorie = categoriefacade.findAll();

            request.setAttribute("listcategorie", listcategorie);
         
            request.setAttribute("membre", membre);

            this.getServletContext().getRequestDispatcher("/modifier_membre.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("nom") != null && request.getParameter("prenom") != null && request.getParameter("email") != null) {

            int idMembre = Integer.parseInt(request.getParameter("idMembre"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");

            membre = membreFacade.find(idMembre);
            
            membre.setNom(nom);
            membre.setPrenom(prenom);
            membre.setEmail(email);
            
            membreFacade.edit(membre);
            

            listcategorie = categoriefacade.findAll();

            request.setAttribute("nomMembre", nom);

            request.setAttribute("listcategorie", listcategorie);

            this.getServletContext().getRequestDispatcher("/modifier_membre.jsp").forward(request, response);

        }
    }

}
