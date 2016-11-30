/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Categorie;
import home.Beans.Membre;
import home.SessionBeans.CategorieFacade;
import static home.controllers.SessionServlet.ATT_SESSION_USER;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author machd
 */
public class HomeServlet extends HttpServlet {

    @EJB
    private CategorieFacade categoriefacade;

    private List<Categorie> listcategorie;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        listcategorie = categoriefacade.findAll();

        request.setAttribute("listcategorie", listcategorie);

        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }

}
