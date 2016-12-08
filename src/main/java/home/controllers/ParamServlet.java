/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Categorie;
import home.Beans.Theme;
import home.SessionBeans.CategorieFacade;
import home.SessionBeans.ThemeFacade;
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
public class ParamServlet extends HttpServlet {

    @EJB
    private CategorieFacade categoriefacade;

    @EJB
    private ThemeFacade themefacade;

    private Theme theme;

    private Categorie categorie;

    private List<Categorie> listCategorie;

    private List<Theme> listTheme;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        listCategorie = categoriefacade.findAll();
        listTheme = themefacade.findAll();

        request.setAttribute("listcategorie", listCategorie);
        request.setAttribute("listtheme", listTheme);

        this.getServletContext().getRequestDispatcher("/parametrage.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("categorie")!=null) {
            
            String libelecat = request.getParameter("categorie");
            
            categorie = new Categorie();
            
            categorie.setIdcategorie(1);
            categorie.setLibeleCat(libelecat);
            
            categoriefacade.create(categorie);
            
            response.sendRedirect(request.getContextPath() + "/ParamServlet");
            
        } else if (request.getParameter("theme")!=null) {
            
            String libeletheme = request.getParameter("theme");
            
            int idCategorie = Integer.parseInt(request.getParameter("choisCategorie"));
            
            Categorie mycategorie = categoriefacade.find(idCategorie);
            
            theme = new Theme();
            
            theme.setIdTheme(1);
            theme.setLibeleTheme(libeletheme);
            theme.setCategorieidcategorie(mycategorie);
            
            themefacade.create(theme);
            
            response.sendRedirect(request.getContextPath() + "/ParamServlet");
            
        }
        
        
        
        
    }

}
