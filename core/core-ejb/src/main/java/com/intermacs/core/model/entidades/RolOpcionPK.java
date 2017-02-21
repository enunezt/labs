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
public class RolOpcionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_opcion", nullable = false)
    private long idOpcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol", nullable = false)
    private long idRol;

    public RolOpcionPK() {
    }

    public RolOpcionPK(int idOpcion, long idRol) {
        this.idOpcion = idOpcion;
        this.idRol = idRol;
    }

    public long getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(long idOpcion) {
        this.idOpcion = idOpcion;
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
        hash += (int) idOpcion;
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolOpcionPK)) {
            return false;
        }
        RolOpcionPK other = (RolOpcionPK) object;
        if (this.idOpcion != other.idOpcion) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.RolOpcionPK[ idOpcion=" + idOpcion + ", idRol=" + idRol + " ]";
    }
    
}
