/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.model.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "catalogo_det", /*catalog = "db_core",*/ schema = "esq_core")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name="CatalogoDetalle.findAll", query="SELECT c FROM CatalogoDet c"),
	@NamedQuery( name = "CatalogoDetalle.findById", query = "SELECT c FROM CatalogoDet c WHERE c.idCatalogoDet = :idCatalogoDetalle" ),
	@NamedQuery( name = "CatalogoDetalle.findByCatalogo", query = "SELECT c FROM CatalogoDet c WHERE c.catalogo = :idCatalogo and c.estado=:estado order by c.idCatalogoDet"),
})
public class CatalogoDet extends EntidadAuditada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_catalogo_det", nullable = false)
    private Long idCatalogoDet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;
    @Size(max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "valor", nullable = false, length = 200)
    private String valor;


    @JoinColumn(name = "id_catalogo", referencedColumnName = "id_catalogo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Catalogo catalogo;


    public CatalogoDet() {
    }

    public CatalogoDet(Long idCatalogoDet) {
        this.idCatalogoDet = idCatalogoDet;
    }

    public CatalogoDet(Long idCatalogoDet, String codigo, String estado, String valor, Date fecRegistro, Date fecCambio, long idUsuarioCambio) {
        this.idCatalogoDet = idCatalogoDet;
        this.codigo = codigo;
        this.estado = estado;
        this.valor = valor;
        this.fecRegistro = fecRegistro;
        this.fecCambio = fecCambio;
        this.idUsuarioCambio = idUsuarioCambio;
    }

    public Long getIdCatalogoDet() {
        return idCatalogoDet;
    }

    public void setIdCatalogoDet(Long idCatalogoDet) {
        this.idCatalogoDet = idCatalogoDet;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogoDet != null ? idCatalogoDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoDet)) {
            return false;
        }
        CatalogoDet other = (CatalogoDet) object;
        if ((this.idCatalogoDet == null && other.idCatalogoDet != null) || (this.idCatalogoDet != null && !this.idCatalogoDet.equals(other.idCatalogoDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.CatalogoDet[ idCatalogoDet=" + idCatalogoDet + " ]";
    }
    
    @Transient
    public String getLabel(){
    	
    	return this.codigo +" - "+this.valor;
    	
    }
    
    @Transient
    public String getLabelValue(){
    	
    	return this.valor;
    	
    }
    
}
