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
public class ModifierMediaServlet extends HttpServlet {

    @EJB
    private MediaFacade mediafacade;
    @EJB
    private CategorieFacade categoriefacade;

    private List<Categorie> listcategorie;

    private int idmedia;

    private int idcategorie;

    private Media media;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("idMedia") != null) {

            idmedia = Integer.parseInt(request.getParameter("idMedia"));

            media = mediafacade.find(idmedia);

            idcategorie = media.getCategorieidcategorie().getIdcategorie();

            if (request.getParameter("supp") != null) {

                mediafacade.remove(media);

                response.sendRedirect(request.getContextPath() + "/MediaServlet?idcategorie=" + idcategorie);

            } else {

                listcategorie = categoriefacade.findAll();

                request.setAttribute("listcategorie", listcategorie);

                request.setAttribute("media", media);

                this.getServletContext().getRequestDispatcher("/modifier_media.jsp").forward(request, response);

            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("titre") != null && request.getParameter("reference") != null && request.getParameter("auteur") != null) {

            int idMedia = Integer.parseInt(request.getParameter("idMedia"));
            String titre = request.getParameter("titre");
            String reference = request.getParameter("reference");
            String auteur = request.getParameter("auteur");

            media = mediafacade.find(idMedia);

            media.setAuteur(auteur);
            media.setTitre(titre);
            media.setReference(reference);

            mediafacade.edit(media);

            listcategorie = categoriefacade.findAll();

            request.setAttribute("titreMedia", titre);

            request.setAttribute("listcategorie", listcategorie);

            this.getServletContext().getRequestDispatcher("/modifier_media.jsp").forward(request, response);

        }
    }

}
