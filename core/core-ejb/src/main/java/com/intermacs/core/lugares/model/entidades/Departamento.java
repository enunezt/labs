/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.lugares.model.entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author eanunezt
 */
@Entity
@XmlRootElement
@Table(schema="esq_core",name="departamentos")
@NamedQueries({@NamedQuery(name=Departamento.NQ_FindALL, query="SELECT d FROM Departamento d"),
@NamedQuery(name=Departamento.NQ_FindByPais, query="SELECT d FROM Departamento d where d.pais=:idPais"),
@NamedQuery(name=Departamento.NQ_FindById, query = "SELECT d FROM Departamento d WHERE d.idDepartamento = :idDepartamento" ),
})
public class Departamento implements Serializable {
	public static final String  NQ_FindALL="Departamento.findAll";
	public static final String  NQ_FindByPais="Departamento.findByPais";
	public static final String  NQ_FindById="Departamento.findById";
	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_departamento", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idDepartamento;
    
    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY,cascade=CascadeType.DETACH)
    private Collection<Ciudad> ciudadCollection;
    
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.DETACH)
    private Pais pais;

    public Departamento() {
    }

    public Departamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Ciudad> getCiudadCollection() {
        return ciudadCollection;
    }

    public void setCiudadCollection(Collection<Ciudad> ciudadCollection) {
        this.ciudadCollection = ciudadCollection;
    }

    public Pais getPais() {
        return pais;
    }

    public void setIdPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Departamento[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
