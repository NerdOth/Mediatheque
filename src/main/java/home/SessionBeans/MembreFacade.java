/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.SessionBeans;

import home.Beans.Membre;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author machd
 */
@Stateless
public class MembreFacade extends AbstractFacade<Membre> {

    @PersistenceContext(unitName = "home_Mediatheque_war_1.0PU")
    private EntityManager em;

    // la methode FindByNom utilise la requete nomee Membre.FindByNom
    public Membre FindByNom(String nom) {
        Query qr = em.createNamedQuery("Membre.findByNom");
        qr.setParameter("nom", nom);
        return (Membre) qr.getSingleResult();
    }

    public List<Membre> FindByFonction() {
        Query qr = em.createNamedQuery("Membre.findByFonction");
        qr.setParameter("fonction", "Membre");
        return qr.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MembreFacade() {
        super(Membre.class);
    }

}
