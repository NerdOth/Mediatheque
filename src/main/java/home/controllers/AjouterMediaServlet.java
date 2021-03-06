/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Categorie;
import home.Beans.Media;
import home.Beans.Theme;
import home.SessionBeans.CategorieFacade;
import home.SessionBeans.MediaFacade;
import home.SessionBeans.ThemeFacade;
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
public class AjouterMediaServlet extends HttpServlet {

    @EJB
    private MediaFacade mediafacade;

    @EJB
    private CategorieFacade categoriefacade;
    
    @EJB
    private ThemeFacade themefacade;
    
    private Theme mytheme;
    
    private Media media;

    private List<Categorie> listcategorie;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        listcategorie = categoriefacade.findAll();

        request.setAttribute("listcategorie", listcategorie);

        this.getServletContext().getRequestDispatcher("/ajouter_media.jsp").forward(request, response);

    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getParameter("titre")!=null && request.getParameter("reference")!=null && request.getParameter("auteur")!=null && request.getParameter("theme")!=null)
        {
            String titre = request.getParameter("titre");
            String reference = request.getParameter("reference");
            String auteur = request.getParameter("auteur");
            int idtheme = Integer.parseInt(request.getParameter("theme"));
            
            mytheme = themefacade.find(idtheme);
            
            media = new Media();
            
            media.setIdMedia(1);
            media.setAuteur(auteur);
            media.setTitre(titre);
            media.setReference(reference);
            media.setThemeidTheme(mytheme);
            media.setCategorieidcategorie(mytheme.getCategorieidcategorie());
            
            mediafacade.create(media);
            
            request.setAttribute("titreMedia", titre);

            listcategorie = categoriefacade.findAll();

            request.setAttribute("listcategorie", listcategorie);

            this.getServletContext().getRequestDispatcher("/ajouter_media.jsp").forward(request, response);
          
        }
        
        
    }

    
}
