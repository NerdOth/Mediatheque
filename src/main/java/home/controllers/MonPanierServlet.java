/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Categorie;
import home.Beans.Emprunt;
import home.Beans.Membre;
import home.SessionBeans.CategorieFacade;
import home.SessionBeans.EmpruntFacade;
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
public class MonPanierServlet extends HttpServlet {

    private List<Emprunt> listEmprunt;

    @EJB
    private EmpruntFacade empruntfacade;
    @EJB
    private CategorieFacade categoriefacade;

    private List<Categorie> listcategorie;

    private int size;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Membre membre = (Membre) session.getAttribute(ATT_SESSION_USER);

        listEmprunt = empruntfacade.FindByMembre(membre);

        size = listEmprunt.size();

        listcategorie = categoriefacade.findAll();

        if (request.getParameter("validerList") != null) {
            for (Emprunt emp : listEmprunt) {
                emp.setPanier(Boolean.FALSE);
                empruntfacade.edit(emp);
            }

            request.setAttribute("listcategorie", listcategorie);

            this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);

        } else {

            if (size!=0) {
                request.setAttribute("sizeList", size);

                request.setAttribute("listEmpruntPanier", listEmprunt);

            }

            request.setAttribute("listcategorie", listcategorie);

            this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
