package com.intermacs.commons.dtos;
import java.io.Serializable;
import java.util.Map;

import com.intermacs.commons.enums.EParametro;
import com.intermacs.commons.enums.IParametro;


/**
 * @author ENUNEZT
 * 
 */
@SuppressWarnings("serial")
public abstract class BaseRequestResponseDTO implements Serializable {
	protected Map<IParametro, Object> param;
	protected boolean pagination;	
	public BaseRequestResponseDTO() {
		super();	
	}

	/**
	 * @return the param
	 */
	public Object getParam(IParametro param) {
		Object retorno = null;
		if (param != null)
			retorno = this.param.get(param);

		return retorno;
		// return messages;
	}
	
	/**
	 * 
	 * Returns true if this map contains a mapping for the specified key. 
	 * More formally, returns true if and only if this map contains a mapping 
	 * for a key k such that (key==null ? k==null : key.equals(k)). 
	 * (There can be at most one such mapping.)
	 * @return the param
	 */
	public boolean containParam(IParametro param) {
		boolean retorno = false;
		//if (param != null)
			retorno = this.param.containsKey(param);

		return retorno;
		// return messages;
	}

	/**
	 * @param param
	 *            the param to set
	 */
	public void setParam(IParametro param, Object object) {

		this.param.put(param, object);

	}

	/**
	 * @param param
	 *            the param to set
	 */
	public void removeParam(IParametro param) {
		// Object retorno=null;
		if (param != null/* && this.param.containsKey(param)*/)
			this.param.remove(param);

	}

	

	/**
	 * @return the param
	 */
	public Map<IParametro, Object> getParams() {
	    return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParams(Map<IParametro, Object> params) {
	    this.param = params;
	}
	
	
	
	

	/**
	 * Retorna la entidad/objeto gestionado
	 * @return (T)Object
	 * @return the object
	 */
//	@SuppressWarnings("unchecked")
	public /*<T> T*/Object getObject() {
	    return getParam(EParametro.OBJECT);
	}

	/**
	 * Asigna la entidad/objeto gestionado
	 * @param object the object to set
	 */
	public /*<T>*/void setObject(Object object) {
	    setParam(EParametro.OBJECT, object);
	}

	/**
	 * @return the pagination
	 */
	public boolean isPagination() {
	    return pagination;
	}

	

	/**
	 * @return the paginationDTO
	 */
	public PaginationDTO getPaginationDTO() {
	    return (PaginationDTO) getParam(EParametro.Paginable);
	}

	/**
	 * @param paginationDTO the paginationDTO to set
	 */
	public void setPaginationDTO(PaginationDTO paginationDTO) {
	    this.pagination=true;
		setParam(EParametro.Paginable, paginationDTO);
	}


}
