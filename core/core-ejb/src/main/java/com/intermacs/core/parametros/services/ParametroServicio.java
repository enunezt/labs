package com.intermacs.core.parametros.services;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.parametros.model.ParametrosModel;
import com.intermacs.exceptions.ServicioExcepcion;
 
	


/**\verbatim**/
@Servicio(clase="ParametroServicio",descripcion="Contrato para la Logica de negocio para Parametro")
/**\endverbatim**/
public interface ParametroServicio  extends ParametrosModel<ServicioExcepcion>{

/**
     * Permite insertar un regisgtro en la respectiva tabla Parametros y sus ParamtrosDet
     * @param request con Entidad (Parametro) en RequestDTO.Object
     * @return  ResponseDTO.Object con entidad gestionada (Parametro)
     * @throws E
     */
	 public ResponseDTO crearParametro(RequestDTO request) throws ServicioExcepcion;
	 /**
	     * Permite actualizar un regisgtro en la respectiva  tabla Parametros y sus ParamtrosDet
	     * @param request con Entidad (Parametro) en RequestDTO.Object
		 * @return  ResponseDTO.Object con entidad gestionada (Parametro)
	     * @throws E
	     */
     public ResponseDTO actualizarParametro(RequestDTO request) throws ServicioExcepcion;

     

}