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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.intermacs.core.enums.ETipoOpcion;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "opciones", /*catalog = "db_core",*/ schema = "esq_core")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
    @NamedQuery(name = "Opcion.findByIdOpcion", query = "SELECT o FROM Opcion o WHERE o.idOpcion = :idOpcion")})
public class Opcion extends EntidadAuditada implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opcion", nullable = false)
    private Long idOpcion;
    
    
    @Size(max = 100)
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "modulo", nullable = false)
    private Integer modulo;
    
    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
    private ETipoOpcion tipo;
    
    
    @Size(max = 100)
    @Column(name = "url", length = 100)
    private String url;
    
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    @ManyToOne(fetch = FetchType.EAGER)
    private Menu menu;
   

    public Opcion() {
    }

    public Opcion(Long idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Opcion(Long idOpcion, int modulo, ETipoOpcion tipo, Date fecRegistro) {
        this.idOpcion = idOpcion;
        this.modulo = modulo;
        this.tipo = tipo;
        this.fecRegistro = fecRegistro;
    }

    public Long getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Long idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(Integer modulo) {
        this.modulo = modulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ETipoOpcion getTipo() {
        return tipo;
    }

    public void setTipo(ETipoOpcion tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

  /************************************************/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Opcion[ idOpcion=" + idOpcion + " ]";
    }
    
}
