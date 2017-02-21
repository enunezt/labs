/**
 * 
 */
package com.intermacs.commons.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intermacs.commons.enums.EParametro;
import com.intermacs.commons.enums.IParametro;


/**
 * @author Edgar
 * 
 */
@SuppressWarnings("serial")
public class ResponseDTO extends BaseRequestResponseDTO  implements Serializable {
	
	//private Map<IParametro, Object> param;
	private boolean resultBoolean;
	private boolean error;	
	private IMensajesDTO mensaje;
	private Map<Integer,IMensajesDTO> msgMap;
	
	//private Class<?> _type;

	public ResponseDTO() {
		super();
		param = new HashMap<IParametro, Object>();
		msgMap=new HashMap<Integer, IMensajesDTO>();
	}
	
	public ResponseDTO(Object object) {
		super();
		param = new HashMap<IParametro, Object>();
		msgMap=new HashMap<Integer, IMensajesDTO>();
		setObject(object);
		
	}
	
	/* public <T> T getObjectType(){
				return (T) getObject();	 
	 };*/
	
	/*public <_type extends Object> _type getCastObject() {
		if(getObject()!=null)
	    return (_type) getObject();
		else return null;
	}*/
	
	public ResponseDTO(List<?> objectList) {
		super();
		param = new HashMap<IParametro, Object>();
		param.put(EParametro.ResultList,objectList);
		resultBoolean=objectList!=null && !objectList.isEmpty();
	}
	@Override
	public void setParam(IParametro param, Object object) {
		this.param.put(param, object);
		resultBoolean=object!=null;
	}

	public boolean isExisteResultado() {
		return resultBoolean;
	}

	public void setExisteResultado(boolean resultBoolean) {
		this.resultBoolean = resultBoolean;
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Verifica se la lista resultado es vacia
	 * @return
	 */
	public boolean resultListIsEmpty(){
		
		Collection lst=(Collection) getParam(EParametro.ResultList);
	if(lst!=null)
		return lst.isEmpty();
		else
		return	true;	
	}
	
	/**
	 *Retorna la lista resultado EParametro.ResultList
	 * @return List
	 */
	public Collection<?> getResultList(){		
	return (Collection<?>) getParam(EParametro.ResultList);
	}
	
	/**
	 * Asigna la entidad/objeto gestionado
	 * @param object the object to set
	 */
	@Override
	public /*<T>*/void setObject(Object object) {
	    setParam(EParametro.OBJECT, object);
	    resultBoolean=object!=null;
	   // _type=object!=null?object.getClass():null;
	}
	
	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}
	
	public void addMensaje(IMensajesDTO mensaje){
		this.mensaje=mensaje;
		if(mensaje.isTipoError())
		error=true;
		
		this.msgMap.put(this.msgMap.size()+1, mensaje);
	}

	
	/**
	 * Retorna descripciones de errores generados y controlados
	 * @return
	 */
	public IMensajesDTO[] getMensajes(){	
		
		 Collection<IMensajesDTO> values = this.msgMap.values();
		 		
		return values.toArray(new IMensajesDTO[values.size()]);
	}

	/**
	 * @return the mensaje
	 */
	public IMensajesDTO getMensaje() {
		return mensaje;
	}
	
	public void copyErrors(ResponseDTO other){
		this.msgMap.putAll(other.msgMap);
		error=other.error;
		
	}
}
