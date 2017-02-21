package com.intermacs.images.model.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.intermacs.core.model.entidades.EntidadAuditada;

/**
 * Entity implementation class for Entity: Imagen
 *
 */
@Entity
@Table(schema="esq_core",name="imagenes")
@NamedQueries(
{@NamedQuery( name = "Imagen.findAllEntidad", query = "SELECT i FROM Imagen i WHERE i.idEntidad=:idEntidad" ),
@NamedQuery( name = "Imagen.findById", query = "SELECT i FROM Imagen i WHERE i.idImagen = :idImagen" ),
@NamedQuery( name = "Imagen.findIdByEnte", query = "SELECT i.idImagen FROM Imagen i WHERE i.idEntidad=:idEntidad AND i.tipo=:tipo"),
@NamedQuery( name = "Imagen.findByEnte", query = "SELECT i FROM Imagen i WHERE i.idEntidad=:idEntidad AND i.tipo=:tipo" )
} )
public class Imagen extends EntidadAuditada implements Serializable {
	private static final long serialVersionUID = 7102771536127786524L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_Imagen",unique=true, nullable=false)
	private Long idImagen;	
	
	private String nombre;
	private EImagen tipo;
	private Long idEntidad;
	private byte[] content; 
	private ETipoImagen extension;

	public Imagen() {
		super();
	}
   
	
	
	public Long getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Long idImagen) {
		this.idImagen = idImagen;
	}
	
	@Column(length=20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Enumerated(EnumType.ORDINAL) 
	public EImagen getTipo() {
		return tipo;
	}


	public void setTipo(EImagen tipo) {
		this.tipo = tipo;
	}


	@Column(nullable=false)
	public Long getIdEntidad() {
		return idEntidad;
	}


	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	

	@Lob
	@Column( name = "content",length = 20000)
	@Basic(fetch = FetchType.LAZY)
	public byte[] getContent() {
		return content;
	}


	public void setContent(byte[] content) {
		this.content = content;
	}


	@Column(nullable=false)
	@Enumerated(EnumType.STRING) 
	public ETipoImagen getExtension() {
		return extension;
	}


	public void setExtension(ETipoImagen extension) {
		this.extension = extension;
	}

	
}
