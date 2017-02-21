/**
 * 
 */
package com.intermacs.core.notificaciones.model.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author intermacs-2
 *
 */
public class NotificacionesDTO {
	/**
	 * Asunto del mensaje
	 */
	private String asunto;
	/**
	 * contenido del mensaje en html
	 */
	private String contenido;
	
	/**
	 * Plantilla para el mensaje
	 */
	private Long idPlantilla;
	/**
	 * listado de idPersona al que se le envía el correo
	 * opcional
	 */
	private List<Long> personasDestino;
	
	/**
	 * Listado de correos al que se le envía el correo
	 * 
	 */
	private List<String> correosDestino;
	
	/**
	 * correo que realiza el envío
	 */
	private String correoRemitente;
	
	/**
	 * IdPersona que realiza el envío
	 */
	private Long idPersonaRemitente;
	
	
	/**
	 * archivos adjuntos
	 */
	private List<File>  adjuntos;
	
	
	/**
	 * corresponde al tipo de mensaje a enviar text o html
	 * email.getContenido(),"text/plain"
	 * default "text/plain"
	 */
	private String tipo;
	
	

	public NotificacionesDTO(String asunto, String contenido, Long idPlantilla,
			List<Long> personasDestino, String correoRemitente, File adjunto) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.idPlantilla = idPlantilla;
		this.personasDestino = personasDestino;
		this.correoRemitente = correoRemitente;
		addFile(adjunto); 
	}
	

	public NotificacionesDTO(String asunto, String contenido, Long idPlantilla,
			String correoDestino, String correoRemitente,File adjunto) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.idPlantilla = idPlantilla;
		this.correoRemitente = correoRemitente;
		addCorreoDestino(correoDestino); 
		addFile(adjunto);
	}
	
	
	public NotificacionesDTO(String asunto, String contenido, Long idPlantilla,
			Long idPersonaDestino, Long idPersonaRemitente,File adjunto) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.idPlantilla = idPlantilla;
		this.idPersonaRemitente=idPersonaRemitente;
		addPersonaDestino(idPersonaDestino); 
		addFile(adjunto);
	}
	
	public NotificacionesDTO(String asunto, String contenido, Long idPlantilla,
			Long idPersonaDestino, Long idPersonaRemitente) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.idPlantilla = idPlantilla;
		this.idPersonaRemitente=idPersonaRemitente;
		addPersonaDestino(idPersonaDestino); 
	}
	
	public NotificacionesDTO(String asunto, String contenido, Long idPlantilla,
			String correoDestino, String correoRemitente) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.idPlantilla = idPlantilla;
		this.correoRemitente = correoRemitente;
		addCorreoDestino(correoDestino); 
	}
	
	public NotificacionesDTO(String asunto, String contenido, Long idPlantilla,
			String correoDestino, String correoRemitente, String tipo) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.idPlantilla = idPlantilla;
		this.correoRemitente = correoRemitente;
		this.tipo=tipo;
		addCorreoDestino(correoDestino); 
	}
	
	
	


	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Long getIdPlantilla() {
		return idPlantilla;
	}

	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

	public List<Long> getPersonasDestino() {
		return personasDestino;
	}
	/**
	 * verifica si el listado de personasDestino viene null o vacio
	 * @return
	 */
	public boolean isPersonasDestino(){
		return personasDestino!=null && personasDestino.isEmpty();
	}

	public void setPersonasDestino(List<Long> personasDestino) {
		this.personasDestino = personasDestino;
	}

	public List<String> getCorreosDestino() {
		return correosDestino;
	}

	public void setCorreosDestino(List<String> correosDestino) {
		this.correosDestino = correosDestino;
	}

	public String getCorreoRemitente() {
		return correoRemitente;
	}

	public void setCorreoRemitente(String correoRemitente) {
		this.correoRemitente = correoRemitente;
	}

	public Long getIdPersonaRemitente() {
		return idPersonaRemitente;
	}

	public void setIdPersonaRemitente(Long idPersonaRemitente) {
		this.idPersonaRemitente = idPersonaRemitente;
	}
/**
 * Indica si el contenido se completa apartir de una plantilla
 * 
 * @return
 */
	public boolean isOrigenPlantilla(){
		
		return this.idPlantilla!=null;
	}
	
	/**
	 * Agrega un archivo al listado de adjunto
	 * @param archivo
	 */
	public void addFile(File archivo){
		if(adjuntos==null){
			adjuntos=new ArrayList<File>();
		}
		adjuntos.add(archivo);
	}
	
	/**
	 * Agrega un correo al listado correosDestino
	 * @param correo
	 */
	public void addCorreoDestino(String correo){
		if(correosDestino==null){
			correosDestino=new ArrayList<String>();
		}
		if(correo!=null && !correosDestino.contains(correo))		
		correosDestino.add(correo);
	}
	
	/**
	 * Agrega un idPersona al listado personasDestino
	 * @param idPersona
	 */
	public void addPersonaDestino(Long idPersona){
		if(personasDestino==null){
			personasDestino=new ArrayList<Long>();
		}
		if(idPersona!=null && !personasDestino.contains(idPersona))	
		personasDestino.add(idPersona);
	}


	public String getTipo() {
		
		if(tipo==null){
			tipo="text/plain";
		}
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

}
