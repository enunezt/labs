/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.model.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "menu", /*catalog = "db_core",*/ schema = "esq_core", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_menu"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByIdMenu", query = "SELECT m FROM Menu m WHERE m.idMenu = :idMenu"),
    @NamedQuery(name = "Menu.findByIdPadre", query = "SELECT m FROM Menu m WHERE m.idPadre = :idPadre")
    })
public class Menu extends EntidadAuditada implements Serializable {
    private static final long serialVersionUID = 1L;
	private static final String  SEQ_NAME="menu_id_menu_seq";
	private static final String  SEQ_ALIAS="menuIdMenuSeq";
    
    @Id		
	@SequenceGenerator(schema="esq_core", name = Menu.SEQ_ALIAS, sequenceName =Menu.SEQ_NAME, allocationSize = 1, initialValue = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = Menu.SEQ_ALIAS )
	@Column(name="id_menu",unique=true, nullable=false)
    private Long idMenu;
    
    @Size(max = 100)
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    
    @Column(name = "id_padre")
    private BigInteger idPadre;
    
    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;
    

    public Menu() {
    }

    public Menu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Menu(Long idMenu, Date fecRegistro) {
        this.idMenu = idMenu;
        this.fecRegistro = fecRegistro;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(BigInteger idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Menu[ idMenu=" + idMenu + " ]";
    }
    
}
