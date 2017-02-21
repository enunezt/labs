/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.lugares.model.entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author eanunezt
 */
@Entity
@Table(name = "paises", /*catalog = "db_core",*/ schema = "esq_core")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findByIdPais", query = "SELECT p FROM Pais p WHERE p.idPais = :idPais"),
    @NamedQuery(name = "Pais.findByIso2", query = "SELECT p FROM Pais p WHERE p.iso2 = :iso2"),
    @NamedQuery(name = "Pais.findByIso3", query = "SELECT p FROM Pais p WHERE p.iso3 = :iso3"),
    @NamedQuery(name = "Pais.findByIsoNum", query = "SELECT p FROM Pais p WHERE p.isoNum = :isoNum"),
    @NamedQuery(name = "Pais.findByNombre", query = "SELECT p FROM Pais p WHERE p.nombre = :nombre")})
public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pais", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idPais;
    @Size(max = 2)
    @Column(name = "iso2", length = 2)
    private String iso2;
    @Size(max = 3)
    @Column(name = "iso3", length = 3)
    private String iso3;
    @Column(name = "iso_num")
    private Integer isoNum;
    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
    private Collection<Departamento> departamentoCollection;

    public Pais() {
    }

    public Pais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Integer getIsoNum() {
        return isoNum;
    }

    public void setIsoNum(Integer isoNum) {
        this.isoNum = isoNum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Departamento> getDepartamentoCollection() {
        return departamentoCollection;
    }

    public void setDepartamentoCollection(Collection<Departamento> departamentoCollection) {
        this.departamentoCollection = departamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Pais[ idPais=" + idPais + " ]";
    }
    
}
