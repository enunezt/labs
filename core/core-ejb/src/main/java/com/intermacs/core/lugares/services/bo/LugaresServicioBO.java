 
	
package com.intermacs.core.lugares.services.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.intermacs.commons.annotations.Servicio;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.lugares.model.entidades.Ciudad;
import com.intermacs.core.lugares.model.entidades.Departamento;
import com.intermacs.core.lugares.model.entidades.Pais;
import com.intermacs.core.lugares.services.LugaresServicio;
import com.intermacs.core.services.GenericoServices;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.security.ContextoPrincipal;

@Stateless
@Local(LugaresServicio.class)
@Servicio(clase="PaisServicioImpl",descripcion="Implementacion Logica de negocio de Lugares")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class LugaresServicioBO extends GenericoServices implements LugaresServicio{
    
  	@Inject
	private EntityManager em;
  	
  	@Resource 
  	SessionContext ctx;

    /* (non-Javadoc)
     * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarPaises(co.edu.unitropico.common.dtos.RequestDTO)
     */
    @Override
    /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
    public ResponseDTO consultarPaises(RequestDTO request)
	    throws ServicioExcepcion {
	try {
	    request.setParam(EParametro.NamedQuery,"Pais.findAll");
	    return findWithNamedQuery(request);
	
	} catch (Exception e) {
			throw new ServicioExcepcion(e);
		}
    }

    /* (non-Javadoc)
     * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarDepartamentos(co.edu.unitropico.common.dtos.RequestDTO)
     */
    @Override
    /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
    public ResponseDTO consultarDepartamentos(RequestDTO request)
	    throws ServicioExcepcion {
	try {
	    
	    Map<String,Object> parameters=new HashMap<String, Object>();
	    Pais idPais=(Pais) request.getParam(EParametro.Entidad);
	    if(idPais==null){
		 idPais=new Pais();
		    idPais.setIdPais((Integer)request.getParam(EParametro.IdEntidad));	 
		    }  
	   
		parameters.put("idPais",idPais);		
		request.setParam(EParametro.NamedQuery,"Departamento.findByPais");
		request.setParam(EParametro.ParemtrosQuery,parameters);
		
		
		return findWithNamedQuery(request);
	} catch (Exception e) {
			throw new ServicioExcepcion(e);
		}
    }

    /* (non-Javadoc)
     * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarCiudades(co.edu.unitropico.common.dtos.RequestDTO)
     */
    @Override
    /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
    public ResponseDTO consultarCiudades(RequestDTO request)
	    throws ServicioExcepcion {
	try {
	    Departamento idDepartamento=(Departamento) request.getParam(EParametro.Entidad);
	    if(idDepartamento==null){
	    	
	    	if(request.getParam(EParametro.IdEntidad)==null)
	    		throw new ServicioExcepcion("Error en la consulta de Ciudades, Departamento con valor null");
	    		
		idDepartamento=new Departamento();
		
		  idDepartamento.setIdDepartamento((Integer)request.getParam(EParametro.IdEntidad));
	    }    
	    Map<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("idDepartamento", idDepartamento);
         	request.setParam(EParametro.NamedQuery,"Ciudad.findByDepartamento");
		request.setParam(EParametro.ParemtrosQuery,parameters);
	    return findWithNamedQuery(request);
	} catch (Exception e) {
			throw new ServicioExcepcion(e);
		}
    }

    /* (non-Javadoc)
     * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarComunas(co.edu.unitropico.common.dtos.RequestDTO)
     */
    @Override
    /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
    public ResponseDTO consultarComunas(RequestDTO request)
	    throws ServicioExcepcion {
	/*try {
	    
		    Map<String,Object> parameters=new HashMap<String, Object>();
			parameters.put("idEntidad", (Long)request.getParam(EParametro.IdEntidad));
			parameters.put("tipo", (EParametro)request.getParam(EParametro.ImagenTipo));

			
			request.setParam(EParametro.NamedQuery,"Imagen.findByEnte");
			request.setParam(EParametro.ParemtrosQuery,parameters);
			
			ResponseDTO resp=imagenDAO.findWithNamedQuery(request);
			
			
			//Imagen.findById
			Set<Imagen> ret=null;
			@SuppressWarnings("unchecked")
			List<Imagen> lst=(List<Imagen>) resp.getParam(EParametro.ResultList);
			
			if(lst!=null && !lst.isEmpty()){				
				ret=new HashSet<Imagen>(lst);
				for (Imagen imagen : ret) {
					imagen.getContent();
				}
			}			
			ResponseDTO retorno=new ResponseDTO();
			retorno.setParam(EParametro.Set, ret);
			return retorno;
			
		} catch (DAOExcepcion e) {
			throw new ServicioExcepcion(e);
		} catch (Exception e) {
				throw new ServicioExcepcion(e);
			}*/
	
	return null;
    }

    /* (non-Javadoc)
     * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarBarrios(co.edu.unitropico.common.dtos.RequestDTO)
     */
    @Override
    /**\verbatim**/@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)/**\endverbatim**/
    public ResponseDTO consultarBarrios(RequestDTO request)
	    throws ServicioExcepcion {
	// TODO Auto-generated method stub
	return null;
    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	/* (non-Javadoc)
	 * @see co.edu.unitropico.core.lugares.model.LugaresModel#consultarDepartamento(co.edu.unitropico.common.dtos.RequestDTO)
	 */
	@Override
	public ResponseDTO consultarDepartamento(RequestDTO request)
			throws ServicioExcepcion {
		try {
		    
		 //   Map<String,Object> parameters=new HashMap<String, Object>();
		    Departamento idDepartamento=(Departamento) request.getParam(EParametro.Entidad);
		    if(idDepartamento==null){
		    	idDepartamento=new Departamento();
		    	idDepartamento.setIdDepartamento((Integer)request.getParam(EParametro.IdEntidad));	 
			    }  
		   
			//parameters.put("idDepartamento",idDepartamento);		
			request.setParam(EParametro.NamedQueryLocal,Departamento.NQ_FindById);
			request.setParam(EParametro.ClassEntidad,Departamento.class);
			request.setParam(EParametro.ID_OBJECT,idDepartamento.getIdDepartamento());
			
			idDepartamento=(Departamento) buscarPorIdNamedQeryId(request);
			//idDepartamento.getCiudades();
			
			ResponseDTO response=new ResponseDTO(idDepartamento);
			return response;
		} catch (Exception e) {
				throw new ServicioExcepcion(e);
			}
	} 
	
	
	
	 public List<Ciudad> getCiudades(String aTextoDigitado)
	   throws ServicioExcepcion {
		try {
	     List<Ciudad> municipios = null;
	     String jpql = "";
	     try {
	       jpql = jpql + " SELECT p ";
	       jpql = jpql + " FROM Ciudad p WHERE 1=1";
	       
	       if ((aTextoDigitado != null) && (!aTextoDigitado.trim().equals(""))  && (!aTextoDigitado.trim().equals("*"))) {
	         jpql = jpql + " AND (";
	         jpql = jpql + " UPPER(CONCAT(p.nombre,' ', p.departamento.nombre)) LIKE '%" + aTextoDigitado.toUpperCase().trim() + "%'";
	         jpql = jpql + " OR UPPER(CONCAT(p.departamento,' ', p.nombre )) LIKE '%" + aTextoDigitado.toUpperCase().trim() + "%'";
	         jpql = jpql + " )";
	       }
	       
	       /*if ((aTextoDigitado != null) && (!aTextoDigitado.trim().equals("")) && (aNumero)) {
	         jpql = jpql + " AND p.nombre = " + aTextoDigitado;
	       }*/
	       
	 
	       jpql = jpql + " ORDER BY p.nombre";
	       
	       Query query = this.em.createQuery(jpql);
	       municipios = query.setMaxResults(20).getResultList();
	       
	       if(municipios!=null){
	    	   for (Iterator iterator = municipios.iterator(); iterator.hasNext();) {
				Ciudad ciudad = (Ciudad) iterator.next();
				ciudad.getDepartamento();
				
			}
	       }
	     }
	     catch (Exception e) {
	       e.printStackTrace();
	     }
	     return municipios;
	     
		} catch (Exception e) {
			throw new ServicioExcepcion(e);
		}
	   }

	@Override
	protected ContextoPrincipal getContextoPrincipal() {
		
		return  (ContextoPrincipal) ctx.getCallerPrincipal();
	}
}
