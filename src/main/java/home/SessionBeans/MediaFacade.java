/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.SessionBeans;

import home.Beans.Categorie;
import home.Beans.Media;
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
public class MediaFacade extends AbstractFacade<Media> {
    @PersistenceContext(unitName = "home_Mediatheque_war_1.0PU")
    private EntityManager em;
    
    // la methode FindByCategorie utilise la requete nomee Media.findBycategorie 
    public List<Media> FindByCategorie(Categorie categorie){
        Query qr=em.createNamedQuery("Media.findBycategorie");
        qr.setParameter("categorie", categorie);
        return qr.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        em.flush();
        em.clear();
        em.getEntityManagerFactory().getCache().evictAll();
        return em;
    }

    public MediaFacade() {
        super(Media.class);
    }
    
}
