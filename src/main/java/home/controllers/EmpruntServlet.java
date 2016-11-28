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
public class EmpruntServlet extends HttpServlet {

    private List<Emprunt> listEmprunt;

    @EJB
    private EmpruntFacade empruntfacade;

    @EJB
    private CategorieFacade categoriefacade;

    private List<Categorie> listcategorie;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Membre membre = (Membre) session.getAttribute(ATT_SESSION_USER);

        listEmprunt = empruntfacade.FindByMem(membre);

        listcategorie = categoriefacade.findAll();

        request.setAttribute("listEmpruntPanier", listEmprunt);
        request.setAttribute("listcategorie", listcategorie);

        this.getServletContext().getRequestDispatcher("/emprunts.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
