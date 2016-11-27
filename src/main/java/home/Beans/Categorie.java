/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.Beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author machd
 */
@Entity
@Table(name = "Categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findByIdcategorie", query = "SELECT c FROM Categorie c WHERE c.idcategorie = :idcategorie"),
    @NamedQuery(name = "Categorie.findByLibeleCat", query = "SELECT c FROM Categorie c WHERE c.libeleCat = :libeleCat"),
    @NamedQuery(name = "Categorie.findByDescription", query = "SELECT c FROM Categorie c WHERE c.description = :description")})
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcategorie")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcategorie;
    @Size(max = 100)
    @Column(name = "libeleCat")
    private String libeleCat;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorieidcategorie")
    private Collection<Media> mediaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorieidcategorie")
    private Collection<Theme> themeCollection;

    public Categorie() {
    }

    public Categorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Integer getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getLibeleCat() {
        return libeleCat;
    }

    public void setLibeleCat(String libeleCat) {
        this.libeleCat = libeleCat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Media> getMediaCollection() {
        return mediaCollection;
    }

    public void setMediaCollection(Collection<Media> mediaCollection) {
        this.mediaCollection = mediaCollection;
    }

    @XmlTransient
    public Collection<Theme> getThemeCollection() {
        return themeCollection;
    }

    public void setThemeCollection(Collection<Theme> themeCollection) {
        this.themeCollection = themeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategorie != null ? idcategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idcategorie == null && other.idcategorie != null) || (this.idcategorie != null && !this.idcategorie.equals(other.idcategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "home.Beans.Categorie[ idcategorie=" + idcategorie + " ]";
    }

}
