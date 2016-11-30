/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Categorie;
import home.Beans.Emprunt;
import home.Beans.Media;
import home.SessionBeans.CategorieFacade;
import home.SessionBeans.EmpruntFacade;
import home.SessionBeans.MediaFacade;
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
public class RendreMediaServlet extends HttpServlet {

    @EJB
    private MediaFacade mediafacade;
    @EJB
    private CategorieFacade categoriefacade;
    @EJB
    private EmpruntFacade empruntfacade;

    private Emprunt emprunt;

    private List<Media> listMedia;

    private List<Emprunt> listEmprunt;

    private List<Media> listEmpruntMedia;

    private List<Categorie> listcategorie;

    private int idEmprunt;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("idEmprunt") != null) {
            idEmprunt = Integer.parseInt(request.getParameter("idEmprunt"));

            emprunt = empruntfacade.find(idEmprunt);

            emprunt.setRendu(Boolean.TRUE);

            empruntfacade.edit(emprunt);

            request.setAttribute("rendu", "Rendu");

        }

        listEmprunt = empruntfacade.FindByRendu();

        listcategorie = categoriefacade.findAll();

        request.setAttribute("listEmprunt", listEmprunt);
        request.setAttribute("listcategorie", listcategorie);

        this.getServletContext().getRequestDispatcher("/rendre_media.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
