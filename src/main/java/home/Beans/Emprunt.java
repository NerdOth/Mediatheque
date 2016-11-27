/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.Beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "Emprunt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprunt.findAll", query = "SELECT e FROM Emprunt e"),
    @NamedQuery(name = "Emprunt.findByIdEmprunt", query = "SELECT e FROM Emprunt e WHERE e.idEmprunt = :idEmprunt"),
    @NamedQuery(name = "Emprunt.findByDate", query = "SELECT e FROM Emprunt e WHERE e.date = :date"),
    @NamedQuery(name = "Emprunt.findByMembre", query = "SELECT e FROM Emprunt e WHERE e.panier = true and e.membreidMembre = :membre"),
    @NamedQuery(name = "Emprunt.findByRendu", query = "SELECT e FROM Emprunt e WHERE e.rendu = :rendu")})
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEmprunt")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprunt;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "rendu")
    private Boolean rendu;
    @Column(name = "panier")
    private Boolean panier;
    @JoinColumn(name = "Media_idMedia", referencedColumnName = "idMedia")
    @ManyToOne(optional = false)
    private Media mediaidMedia;
    @JoinColumn(name = "Membre_idMembre", referencedColumnName = "idMembre")
    @ManyToOne(optional = false)
    private Membre membreidMembre;

    public Emprunt() {
    }

    public Emprunt(Integer idEmprunt, Boolean rendu, Boolean panier, Media mediaidMedia, Membre membreidMembre) {
        this.idEmprunt = idEmprunt;
        this.rendu = rendu;
        this.panier = panier;
        this.mediaidMedia = mediaidMedia;
        this.membreidMembre = membreidMembre;
    }

    public Emprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Integer getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getRendu() {
        return rendu;
    }

    public void setRendu(Boolean rendu) {
        this.rendu = rendu;
    }

    public Boolean getPanier() {
        return panier;
    }

    public void setPanier(Boolean panier) {
        this.panier = panier;
    }

    public Media getMediaidMedia() {
        return mediaidMedia;
    }

    public void setMediaidMedia(Media mediaidMedia) {
        this.mediaidMedia = mediaidMedia;
    }

    public Membre getMembreidMembre() {
        return membreidMembre;
    }

    public void setMembreidMembre(Membre membreidMembre) {
        this.membreidMembre = membreidMembre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmprunt != null ? idEmprunt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprunt)) {
            return false;
        }
        Emprunt other = (Emprunt) object;
        if ((this.idEmprunt == null && other.idEmprunt != null) || (this.idEmprunt != null && !this.idEmprunt.equals(other.idEmprunt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "home.Beans.Emprunt[ idEmprunt=" + idEmprunt + " ]";
    }

}
