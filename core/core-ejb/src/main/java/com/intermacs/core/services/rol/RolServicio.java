 
	
package com.intermacs.core.services.rol;

import java.util.List;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.core.model.RolModel;
import com.intermacs.core.model.entidades.Rol;
import com.intermacs.exceptions.ServicioExcepcion;




@Servicio(clase="PerfilServicio",descripcion="Contrato para la Logica de negocio para Perfil")
public interface RolServicio  extends RolModel<ServicioExcepcion>{
	
	/**
	 * Consulta listado de Perfiles segun el Listado de ids que se pasen como parametro
	 * @param ids
	 * @return
	 * @throws ServicioExcepcion
	 */
	 public List<Rol> consultarRolesPorID(Long... ids) throws ServicioExcepcion;
}