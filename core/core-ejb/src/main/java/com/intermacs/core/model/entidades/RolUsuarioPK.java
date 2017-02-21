/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.model.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eanunezt
 */
@SuppressWarnings("serial")
@Embeddable
public class RolUsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private long idUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol", nullable = false)
    private long idRol;

    public RolUsuarioPK() {
    }

    public RolUsuarioPK(long idUsuario, long idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuarioPK)) {
            return false;
        }
        RolUsuarioPK other = (RolUsuarioPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.RolUsuarioPK[ idUsuario=" + idUsuario + ", idRol=" + idRol + " ]";
    }
    
}
