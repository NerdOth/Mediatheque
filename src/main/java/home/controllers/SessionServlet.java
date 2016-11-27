package home.controllers;

import home.Beans.Membre;
import home.SessionBeans.MembreFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {

    @EJB
    private MembreFacade membreFacade;

    private Membre membre;
    private String nom;
    private String motdepasse;

    public static final String MessageErreurEJB = "Nom erroné | Erreur BDD ";
    public static final String MessageErreurMP = "Mot de passe incorrect ";
    public static final String ATT_USER = "membre";
    public static final String ATT_SESSION_USER = "sessionMembre";
    private Map<String, String> erreurs = new HashMap<String, String>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        try {
            nom = request.getParameter("nom");
            motdepasse = request.getParameter("motdepasse");
            membre = membreFacade.FindByNom(nom);

        } catch (Exception e) {
            setErreur("Erreur", e.getMessage());
        }

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if (erreurs.isEmpty()) {
                session.setAttribute(ATT_SESSION_USER, membre);
                response.sendRedirect(request.getContextPath() + "/MediaServlet?idcategorie=2");
            

        } else {
            //session.setAttribute(ATT_SESSION_USER, null);
            request.setAttribute("erreurs", MessageErreurEJB);
            this.getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);

        }

        /* Stockage du formulaire et du bean dans l'objet request */
        //request.setAttribute(ATT_USER, membre);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }
}
