/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.parametros.model.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.intermacs.core.model.entidades.EntidadAuditada;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "parametros", /*catalog = "db_core",*/ schema = "esq_core")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByIdParametro", query = "SELECT p FROM Parametro p WHERE p.idParametro = :idParametro"),
    @NamedQuery(name = "Parametro.findByNombre", query = "SELECT p FROM Parametro p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Parametro.findByEstado", query = "SELECT p FROM Parametro p WHERE p.estado = :estado"),
    @NamedQuery(name = "Parametro.findByDescripcion", query = "SELECT p FROM Parametro p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Parametro.findByFecRegistro", query = "SELECT p FROM Parametro p WHERE p.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Parametro.findByFecCambio", query = "SELECT p FROM Parametro p WHERE p.fecCambio = :fecCambio")})
public class Parametro extends EntidadAuditada implements Serializable {
    private static final long serialVersionUID = 1L;
  

    @Basic(optional = false)    
    @Id
	@SequenceGenerator(schema="esq_core",name="parametros_id_parametro_seq_GENERATOR", sequenceName="parametros_id_parametro_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="parametros_id_parametro_seq_GENERATOR")
	@Column(name="id_parametro", unique=true, nullable=false)
    private Long idParametro;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado", nullable = false)
    private Character estado;
    
    @Size(max = 100)
    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametro", fetch = FetchType.LAZY)
    private Collection<ParametroDet> parametroDetCollection;
  

    public Parametro() {
    }

    public Parametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public Parametro(Long idParametro, String nombre, Character estado, Date fecRegistro) {
        this.idParametro = idParametro;
        this.nombre = nombre;
        this.estado = estado;
        this.fecRegistro = fecRegistro;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   

    @XmlTransient
    public Collection<ParametroDet> getParametroDetCollection() {
        return parametroDetCollection;
    }

    public void setParametroDetCollection(Collection<ParametroDet> parametroDetCollection) {
        this.parametroDetCollection = parametroDetCollection;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Parametro[ idParametro=" + idParametro + " ]";
    }
    
}
