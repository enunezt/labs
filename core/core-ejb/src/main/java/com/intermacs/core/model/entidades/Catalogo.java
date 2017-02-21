/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.model.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "catalogo", /*catalog = "db_core",*/ schema = "esq_core", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre"})})
@XmlRootElement
@NamedQueries({
	@NamedQuery(name="Catalogo.findAll", query="SELECT c FROM Catalogo c"),
	@NamedQuery( name = "Catalogo.findById", query = "SELECT c FROM Catalogo c WHERE c.idCatalogo = :idCatalogo" ),
	@NamedQuery( name = "Catalogo.findIdByName", query = "SELECT c FROM Catalogo c WHERE c.nombre = :nombre")})
public class Catalogo extends EntidadAuditada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_catalogo", nullable = false)
    private Long idCatalogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
   
  
    @OneToMany( mappedBy = "catalogo", fetch = FetchType.LAZY)
    private Collection<CatalogoDet> catalogoDetCollection;

    public Catalogo() {
    }

    public Catalogo(Long idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Catalogo(Long idCatalogo, String descripcion, String nombre, Date fecRegistro, Date fecCambio, long idUsuarioCambio) {
        this.idCatalogo = idCatalogo;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fecRegistro = fecRegistro;
        this.fecCambio = fecCambio;
        this.idUsuarioCambio = idUsuarioCambio;
    }

    public Long getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Long idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @XmlTransient
    public Collection<CatalogoDet> getCatalogoDetCollection() {
        return catalogoDetCollection;
    }

    public void setCatalogoDetCollection(Collection<CatalogoDet> catalogoDetCollection) {
        this.catalogoDetCollection = catalogoDetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogo != null ? idCatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.idCatalogo == null && other.idCatalogo != null) || (this.idCatalogo != null && !this.idCatalogo.equals(other.idCatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Catalogo[ idCatalogo=" + idCatalogo + " ]";
    }
    
}
