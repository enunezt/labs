/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.model.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "roles_opciones", /*catalog = "db_core",*/ schema = "esq_core")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolOpcion.findAll", query = "SELECT r FROM RolOpcion r"),
    @NamedQuery(name = "RolOpcion.findByIdOpcion", query = "SELECT r FROM RolOpcion r WHERE r.rolOpcionPK.idOpcion = :idOpcion"),
    @NamedQuery(name = "RolOpcion.findByIdRol", query = "SELECT r FROM RolOpcion r WHERE r.rolOpcionPK.idRol = :idRol"),
    @NamedQuery(name = "RolOpcion.findByNivel", query = "SELECT r FROM RolOpcion r WHERE r.nivel = :nivel")})
public class RolOpcion extends EntidadAuditada implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolOpcionPK rolOpcionPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivel", nullable = false)
    private short nivel;

    
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY,cascade=CascadeType.DETACH)
    private Rol rol;
    
    @JoinColumn(name = "id_opcion", referencedColumnName = "id_opcion", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER,cascade=CascadeType.DETACH)
    private Opcion opcion;

    public RolOpcion() {
    }

    public RolOpcion(RolOpcionPK rolOpcionPK) {
        this.rolOpcionPK = rolOpcionPK;
    }

    public RolOpcion(RolOpcionPK rolOpcionPK, short nivel, Date fecRegistro) {
        this.rolOpcionPK = rolOpcionPK;
        this.nivel = nivel;
        this.fecRegistro = fecRegistro;
     
    }

    public RolOpcion(int idOpcion, long idRol) {
        this.rolOpcionPK = new RolOpcionPK(idOpcion, idRol);
    }
    
    public RolOpcion(short nivel, Opcion opcion, Rol rol) {
		super();
		this.nivel = nivel;
		this.opcion = opcion;
		this.rol = rol;
		this.rolOpcionPK=new RolOpcionPK(rol.getIdRol().intValue(),opcion.getIdOpcion().intValue());
		
	}
	public RolOpcion( Opcion opcion,Rol rol) {
		super();
	
		this.opcion = opcion;
		this.rol = rol;
		this.rolOpcionPK=new RolOpcionPK(rol.getIdRol().intValue(),opcion.getIdOpcion().intValue());
	}

    public RolOpcionPK getRolOpcionPK() {
        return rolOpcionPK;
    }

    public void setRolOpcionPK(RolOpcionPK rolOpcionPK) {
        this.rolOpcionPK = rolOpcionPK;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

   

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolOpcionPK != null ? rolOpcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolOpcion)) {
            return false;
        }
        RolOpcion other = (RolOpcion) object;
        if ((this.rolOpcionPK == null && other.rolOpcionPK != null) || (this.rolOpcionPK != null && !this.rolOpcionPK.equals(other.rolOpcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.RolOpcion[ rolOpcionPK=" + rolOpcionPK + " ]";
    }
    
}
