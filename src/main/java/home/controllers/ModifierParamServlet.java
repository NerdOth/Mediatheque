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
public class ModifierParamServlet extends HttpServlet {

    @EJB
    private CategorieFacade categoriefacade;

    @EJB
    private ThemeFacade themefacade;

    private Theme theme;

    private Categorie categorie;

    private List<Categorie> listCategorie;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("idcat") != null) {

            int idcat = Integer.parseInt(request.getParameter("idcat"));

            categorie = categoriefacade.find(idcat);

            if (request.getParameter("supp") != null) {

                categoriefacade.remove(categorie);

                listCategorie = categoriefacade.findAll();

                request.setAttribute("listcategorie", listCategorie);

                response.sendRedirect(request.getContextPath() + "/ParamServlet");

            } else {
                request.setAttribute("categorie", categorie);

                listCategorie = categoriefacade.findAll();

                request.setAttribute("listcategorie", listCategorie);

                this.getServletContext().getRequestDispatcher("/modifier_parametrage.jsp").forward(request, response);
            }

        } else if (request.getParameter("idtheme") != null) {

            int idtheme = Integer.parseInt(request.getParameter("idtheme"));

            theme = themefacade.find(idtheme);

            listCategorie = categoriefacade.findAll();

            request.setAttribute("listcategorie", listCategorie);

            if (request.getParameter("supp") != null) {

                themefacade.remove(theme);

                response.sendRedirect(request.getContextPath() + "/ParamServlet");

            } else {

                request.setAttribute("theme", theme);

                this.getServletContext().getRequestDispatcher("/modifier_parametrage.jsp").forward(request, response);

            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("categorie") != null) {

            String libelecat = request.getParameter("categorie");

            int idcategorie = Integer.parseInt(request.getParameter("idcategorie"));

            Categorie mycategorie = categoriefacade.find(idcategorie);

            mycategorie.setLibeleCat(libelecat);

            categoriefacade.edit(mycategorie);

            response.sendRedirect(request.getContextPath() + "/ParamServlet");

        } else if (request.getParameter("theme") != null) {

            String libeletheme = request.getParameter("theme");

            int idtheme = Integer.parseInt(request.getParameter("idtheme"));

            Theme mytheme = themefacade.find(idtheme);

            mytheme.setLibeleTheme(libeletheme);

            themefacade.edit(mytheme);

            response.sendRedirect(request.getContextPath() + "/ParamServlet");

        }
    }

}
