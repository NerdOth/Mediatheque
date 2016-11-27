/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Emprunt;
import home.Beans.Media;
import home.Beans.Membre;
import home.SessionBeans.EmpruntFacade;
import home.SessionBeans.MediaFacade;
import home.SessionBeans.MembreFacade;
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
public class PanierServlet extends HttpServlet {

    @EJB
    private MediaFacade mediafacade;

    @EJB
    private EmpruntFacade empruntfacade;

    @EJB
    private MembreFacade membrefacade;


    private Emprunt emprunt;

    private Membre membre;

    private Media media;

    private int idmedia;
    private int idEmprunt;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        membre = (Membre) session.getAttribute(ATT_SESSION_USER);

        if (request.getParameter("idMedia") != null) {
            idmedia = Integer.parseInt(request.getParameter("idMedia"));

            media = mediafacade.find(idmedia);

            emprunt = new Emprunt(1, false, true, media, membre);

            empruntfacade.create(emprunt);

            response.sendRedirect(request.getContextPath() + "/MediaServlet?idcategorie=" + media.getCategorieidcategorie().getIdcategorie());

        } else if (request.getParameter("idEmprunt") != null) {
            idEmprunt = Integer.parseInt(request.getParameter("idEmprunt"));

            emprunt = empruntfacade.find(idEmprunt);

            empruntfacade.remove(emprunt);

            response.sendRedirect(request.getContextPath() + "/MonPanierServlet");

        } 
            
            
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
