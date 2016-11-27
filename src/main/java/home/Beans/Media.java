/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.Beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "Media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m"),
    @NamedQuery(name = "Media.findByIdMedia", query = "SELECT m FROM Media m WHERE m.idMedia = :idMedia"),
    @NamedQuery(name = "Media.findByTitre", query = "SELECT m FROM Media m WHERE m.titre = :titre"),
    @NamedQuery(name = "Media.findByReference", query = "SELECT m FROM Media m WHERE m.reference = :reference"),
    @NamedQuery(name = "Media.findByAuteur", query = "SELECT m FROM Media m WHERE m.auteur = :auteur"),
    // une nouvelle requete nomee cree, pour rechercher les medias d'une categorie quelquonque 
    @NamedQuery(name = "Media.findBycategorie", query = "SELECT m FROM Media m WHERE m.categorieidcategorie = :categorie"),
    @NamedQuery(name = "Media.findByAnnee", query = "SELECT m FROM Media m WHERE m.annee = :annee")})
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedia;
    @Size(max = 100)
    @Column(name = "titre")
    private String titre;
    @Size(max = 100)
    @Column(name = "reference")
    private String reference;
    @Size(max = 50)
    @Column(name = "auteur")
    private String auteur;
    @Column(name = "annee")
    @Temporal(TemporalType.DATE)
    private Date annee;
    @JoinColumn(name = "Theme_idTheme", referencedColumnName = "idTheme")
    @ManyToOne(optional = false)
    private Theme themeidTheme;
    @JoinColumn(name = "Categorie_idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(optional = false)
    private Categorie categorieidcategorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mediaidMedia")
    private Collection<Emprunt> empruntCollection;

    public Media() {
    }

    public Media(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public Integer getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getAnnee() {
        return annee;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }

    public Theme getThemeidTheme() {
        return themeidTheme;
    }

    public void setThemeidTheme(Theme themeidTheme) {
        this.themeidTheme = themeidTheme;
    }

    public Categorie getCategorieidcategorie() {
        return categorieidcategorie;
    }

    public void setCategorieidcategorie(Categorie categorieidcategorie) {
        this.categorieidcategorie = categorieidcategorie;
    }

    @XmlTransient
    public Collection<Emprunt> getEmpruntCollection() {
        return empruntCollection;
    }

    public void setEmpruntCollection(Collection<Emprunt> empruntCollection) {
        this.empruntCollection = empruntCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedia != null ? idMedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.idMedia == null && other.idMedia != null) || (this.idMedia != null && !this.idMedia.equals(other.idMedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "home.Beans.Media[ idMedia=" + idMedia + " ]";
    }

}
