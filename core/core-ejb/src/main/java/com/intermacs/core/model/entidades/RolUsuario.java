/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.model.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "roles_usuarios", /*catalog = "db_core",*/ schema = "esq_core")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolUsuario.findAll", query = "SELECT r FROM RolUsuario r"),
    @NamedQuery(name = "RolUsuario.findByIdUsuario", query = "SELECT r FROM RolUsuario r WHERE r.rolUsuarioPK.idUsuario = :idUsuario")})
public class RolUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolUsuarioPK rolUsuarioPK;
    
   
   
   /* @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;*/
   
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY,cascade=CascadeType.DETACH)
    private Rol rol;

    public RolUsuario() {
    }

    public RolUsuario(RolUsuarioPK rolUsuarioPK) {
        this.rolUsuarioPK = rolUsuarioPK;
    }
    public RolUsuario(long idUsuario, long idRol) {
        this.rolUsuarioPK = new RolUsuarioPK(idUsuario, idRol);
    }

    public RolUsuarioPK getRolUsuarioPK() {
        return rolUsuarioPK;
    }

    public void setRolUsuarioPK(RolUsuarioPK rolUsuarioPK) {
        this.rolUsuarioPK = rolUsuarioPK;
    }

  
   
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolUsuarioPK != null ? rolUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuario)) {
            return false;
        }
        RolUsuario other = (RolUsuario) object;
        if ((this.rolUsuarioPK == null && other.rolUsuarioPK != null) || (this.rolUsuarioPK != null && !this.rolUsuarioPK.equals(other.rolUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.RolUsuario[ rolUsuarioPK=" + rolUsuarioPK + " ]";
    }
    
}
