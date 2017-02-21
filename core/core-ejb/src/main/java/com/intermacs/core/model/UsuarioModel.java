package com.intermacs.core.model;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.model.entidades.Usuario;


public interface UsuarioModel<E extends Exception> {
	
	/**
     * Permite insertar un regisgtro en la respectiva tabla
     * @param request con Entidad en RequestDTO.Object
     * @return  RequestDTO.Object con entidad gestionada
     * @throws Exepcion
     */
	 public ResponseDTO crearUsuario(RequestDTO request) throws E;
	 /**
	     * Permite actualizar un regisgtro en la respectiva tabla
	     * @param request con Entidad en RequestDTO.Object
		 * @return  RequestDTO.Object con entidad gestionada
	     * @throws Exepcion
	     */
     public ResponseDTO actualizarUsuario(RequestDTO request) throws E;
   /**
    *  Permite eliminar un regisgtro en la respectiva tabla
    * @param request con Entidad en RequestDTO.Object
    * @throws Exepcion
    */
    public void eliminarUsuario(RequestDTO request) throws E;
    
    /**
     * Realiza la busqueda todos los registros o por número de documento
     * @param request con Entidad Usuario en RequestDTO.Object
     * @return List<T> en el parametro EParametro.ResultList
     *         <br/>Usuario en ResponseDTO.Object
     * @throws Exepcion
     */
   
    public ResponseDTO buscarUsuarios(RequestDTO request) throws E;
	/**
	 * Consulta los perfiles disponibles del usuario que pasa como parametro
	 * @param usuario Usuario en RequestDTO.Object
	 * @return  List<Perfil> en parametro EParametrosAdmin.PerfilesDisponiblesUser
	 * @throws Exepcion
	 */
	public ResponseDTO consultarRolesDisponibles(RequestDTO request) throws E;
	
	/**
	 * Construye el menu del usuario. 
	 * @param usuario Usuario en RequestDTO.Object
	 * @return Map<String,Menu>   en parametro EParametrosAdmin.PerfilesUser
	 * @throws Exepcion
	 */
	public ResponseDTO construirMenuUsuario(RequestDTO request) throws E;
	
	
	
	/**
	 * Consulta login de usuario
	 * @param request Usuario en RequestDTO.Object
	 * @return Map<Long, Menu> en parametro EParametro.Map
	 * @throws Exepcion
	 */	
	public ResponseDTO consultarMenu(RequestDTO request) throws E;	
	
	
	/**
	 * Consulta EL usuario por pass y login
	 * @param request Usuario en RequestDTO.Object
	 * @return Usuario en parametro RequestDTO.Object
	 * @throws Exepcion
	 */
	public ResponseDTO consultarUsuario(RequestDTO request /*String usuario, String pass*/)
			throws E;
/**
	 * Permite recuperar la contraseña y usuario de un Usuario del sistema
	 * Debe ir tipoDocumento, numero de documento y correo registrado en el sistema.
	 * @param request Usuario en RequestDTO.Object
	 * @return responseDTO con true/false en responseDTO.resultBoolean
	 * @throws E
	 */
	public ResponseDTO recuperarDatoLoguinUsuario(RequestDTO request)
			throws E;
	
	/**
	 * Consulta un usuario por su IDENTIFICADOR
	 * @param idUsuario
	 * @return
	 * @throws E
	 */
	public Usuario consultarUsuario(Long idUsuario) throws E;;
	
	
	
}
