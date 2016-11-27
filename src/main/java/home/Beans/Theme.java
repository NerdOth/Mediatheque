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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Theme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Theme.findAll", query = "SELECT t FROM Theme t"),
    @NamedQuery(name = "Theme.findByIdTheme", query = "SELECT t FROM Theme t WHERE t.idTheme = :idTheme"),
    @NamedQuery(name = "Theme.findByLibeleTheme", query = "SELECT t FROM Theme t WHERE t.libeleTheme = :libeleTheme")})
public class Theme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTheme")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTheme;
    @Size(max = 45)
    @Column(name = "libeleTheme")
    private String libeleTheme;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "themeidTheme")
    private Collection<Media> mediaCollection;
    @JoinColumn(name = "Categorie_idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(optional = false)
    private Categorie categorieidcategorie;

    public Theme() {
    }

    public Theme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public String getLibeleTheme() {
        return libeleTheme;
    }

    public void setLibeleTheme(String libeleTheme) {
        this.libeleTheme = libeleTheme;
    }

    @XmlTransient
    public Collection<Media> getMediaCollection() {
        return mediaCollection;
    }

    public void setMediaCollection(Collection<Media> mediaCollection) {
        this.mediaCollection = mediaCollection;
    }

    public Categorie getCategorieidcategorie() {
        return categorieidcategorie;
    }

    public void setCategorieidcategorie(Categorie categorieidcategorie) {
        this.categorieidcategorie = categorieidcategorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTheme != null ? idTheme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Theme)) {
            return false;
        }
        Theme other = (Theme) object;
        if ((this.idTheme == null && other.idTheme != null) || (this.idTheme != null && !this.idTheme.equals(other.idTheme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "home.Beans.Theme[ idTheme=" + idTheme + " ]";
    }

}
