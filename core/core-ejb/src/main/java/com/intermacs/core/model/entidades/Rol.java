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
@Table(name = "roles", /*catalog = "db_core",*/ schema = "esq_core", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_rol"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Rol.NQ_FindALL, query = "SELECT r FROM Rol r"),
    @NamedQuery(name = Rol.NQ_FindById, query = "SELECT r FROM Rol r WHERE r.idRol = :idRol"),
    @NamedQuery(name = Rol.NQ_FindByIdIn, query = "SELECT r FROM Rol r WHERE r.idRol  in (:idRol)")})
public class Rol extends EntidadAuditada implements Serializable,Comparable<Rol> {
    private static final long serialVersionUID = 1L;
    private static final String  SEQ_NAME="rol_id_rol_seq";
	private static final String  SEQ_ALIAS="rolIdRolSeq";
	public static final String  NQ_FindALL="Rol.findAll";
	public static final String  NQ_FindById="Rol.findById";
	public static final String  NQ_FindByIdIn="Rol.findByIdIn";
    
    
    @Id    
    @Basic(optional = false)
    @Column(name = "id_rol", nullable = false)
    @SequenceGenerator(schema="esq_core", name = Rol.SEQ_ALIAS, sequenceName =Rol.SEQ_NAME, allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = Rol.SEQ_ALIAS )
    private Long idRol;
    
    @Size(max = 100)
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
    private Collection<RolUsuario> rolUsuarioCollection;*/
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
    private Collection<RolOpcion> rolOpcionCollection;
    
  

    public Rol() {
    }

    public Rol(Long idRol) {
        this.idRol = idRol;
    }

    public Rol(Long idRol, String nombre, Date fecRegistro) {
        this.idRol = idRol;
        this.nombre = nombre;   
        this.fecRegistro=fecRegistro;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
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

    

   /* @XmlTransient
    public Collection<RolUsuario> getRolUsuarioCollection() {
        return rolUsuarioCollection;
    }

    public void setRolUsuarioCollection(Collection<RolUsuario> rolUsuarioCollection) {
        this.rolUsuarioCollection = rolUsuarioCollection;
    }*/

    @XmlTransient
    public Collection<RolOpcion> getRolOpcionCollection() {
        return rolOpcionCollection;
    }

    public void setRolOpcionCollection(Collection<RolOpcion> rolOpcionCollection) {
        this.rolOpcionCollection = rolOpcionCollection;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Rol[ idRol=" + idRol + " ]";
    }

	@Override
	public int compareTo(Rol o) {
		  if(this.hashCode()>o.hashCode()){
              return 1;
  } else if(this.hashCode()<o.hashCode()){
              return -1;
  } else{
              return 0;
  }
	}
    
}
