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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.intermacs.core.enums.ETipoPlantilla;

/**
 * Entity implementation class for Entity: Plantilla
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(schema="esq_core",name="plantillas")
@NamedQueries(
{@NamedQuery( name = Plantilla.NQ_findById, query = "SELECT i FROM Plantilla i WHERE i.idPlantilla = :idPlantilla" )} )
public class Plantilla implements Serializable{
	
	private static final String  SEQ_NAME="plantilla_id_plantilla_seq";
	private static final String  SEQ_ALIAS="plantillaIdplantillaSeq";
	/**SELECT i FROM Plantilla i WHERE i.idPlantilla = :idPlantilla**/
	public static final String  NQ_findById="Plantilla.findById";
	
	private Long idPlantilla;	
	private String nombre;
	private Date fechaRegistro;
	private String contenido; 
	private ETipoPlantilla tipo;

	public Plantilla() {
		super();
	}
   
	
	@Id
	@Column(name="id_Plantilla",unique=true, nullable=false)
	@SequenceGenerator(schema="esq_core", name = Plantilla.SEQ_ALIAS, sequenceName =Plantilla.SEQ_NAME, allocationSize = 1, initialValue = 1 )
	@GeneratedValue( strategy =
	GenerationType.SEQUENCE, generator = Plantilla.SEQ_ALIAS )
	public Long getIdPlantilla() {
		return idPlantilla;
	}

	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	
	@Column(length=100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Enumerated(EnumType.STRING) 
	public ETipoPlantilla getTipo() {
		return tipo;
	}


	public void setTipo(ETipoPlantilla tipo) {
		this.tipo = tipo;
	}

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="fec_registro")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	
	@Column( name = "content",nullable=false)
	@Basic(fetch = FetchType.LAZY)
	@Lob	
	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}



	
}
