/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.SessionBeans;

import home.Beans.Emprunt;
import home.Beans.Media;
import home.Beans.Membre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author machd
 */
@Stateless
public class EmpruntFacade extends AbstractFacade<Emprunt> {

    @PersistenceContext(unitName = "home_Mediatheque_war_1.0PU")
    private EntityManager em;

    // la methode FindByMembre utilise la requete nomee Emprunt.findByMembre
    public List<Emprunt> FindByMembre(Membre membre) {
        Query qr = em.createNamedQuery("Emprunt.findByMembre");
        qr.setParameter("membre", membre);
        return qr.getResultList();
    }

    public List<Emprunt> FindByMem(Membre membre) {
        Query qr = em.createNamedQuery("Emprunt.findByMem");
        qr.setParameter("membre", membre);
        return qr.getResultList();
    }

//    public List<Emprunt> FindByRendu(Boolean rendu){
//        Query qr=em.createNamedQuery("Emprunt.findByRendu");
//        qr.setParameter("rendu", rendu);
//        return qr.getResultList();
//    }
    
    public List<Media> FindByMediaEmprunt() {
        Query qr = em.createNamedQuery("Emprunt.findByMediaEmprunt");
        return qr.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpruntFacade() {
        super(Emprunt.class);
    }

}
