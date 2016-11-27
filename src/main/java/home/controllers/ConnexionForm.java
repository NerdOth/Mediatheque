/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.controllers;

import home.Beans.Membre;
import home.SessionBeans.MembreFacade;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author machd
 */
public final class ConnexionForm {

    @EJB
    private MembreFacade membrefacade;

    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_DB = "base de donnees";

    Membre membre;
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Membre connecterMembre(HttpServletRequest request) {
        /* Récupération des champs du formulaire */
        String nom = getValeurChamp(request, CHAMP_NOM);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        
        
       
        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse(motDePasse);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }

       
        try {
             membre = membrefacade.FindByNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_DB, e.getMessage());
        }

        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return membre;
    }

   

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse(String motDePasse) throws Exception {
        if (motDePasse != null) {
            if (motDePasse.length() < 3) {
                throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir votre mot de passe.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
