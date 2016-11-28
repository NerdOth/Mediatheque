/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Categorie;
import home.Beans.Categorie_;
import home.Beans.Emprunt;
import home.Beans.Media;
import home.Beans.Membre;
import home.SessionBeans.CategorieFacade;
import home.SessionBeans.EmpruntFacade;
import home.SessionBeans.MediaFacade;
import home.SessionBeans.ThemeFacade;
import static home.controllers.SessionServlet.ATT_SESSION_USER;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
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
public class MediaServlet extends HttpServlet {

    @EJB
    private MediaFacade mediafacade;
    @EJB
    private CategorieFacade categoriefacade;
    @EJB
    private EmpruntFacade empruntfacade;

    private Media media;

    private List<Media> listMedia;

    private List<Emprunt> listEmprunt;

    private List<Media> listEmpruntMedia;

    private List<Emprunt> listEmpruntPanier;

    private List<Categorie> listcategorie;

    private int idcategorie;

    private int sizePanier;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        Membre membre = (Membre) session.getAttribute(ATT_SESSION_USER);

//        listEmprunt = empruntfacade.FindByRendu(Boolean.FALSE);
//
//        listEmpruntMedia = listEmprunt.stream().map(Emprunt::getMediaidMedia()).collect(Collectors.toList());
        listEmpruntPanier = empruntfacade.FindByMembre(membre);

        sizePanier = listEmpruntPanier.size();

        listEmpruntMedia = empruntfacade.FindByMediaEmprunt();
        
        listcategorie = categoriefacade.findAll();

        idcategorie = Integer.parseInt(request.getParameter("idcategorie"));

        Categorie categorie = categoriefacade.find(idcategorie);

        listMedia = mediafacade.FindByCategorie(categorie);
        
        listMedia.removeAll(listEmpruntMedia);
        
        request.setAttribute("sizePanier", sizePanier);
        request.setAttribute("membre", membre);
        request.setAttribute("categorie", categorie);
        request.setAttribute("listMedia", listMedia);
        request.setAttribute("listcategorie", listcategorie);

        this.getServletContext().getRequestDispatcher("/list_medias.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
