/**
 * 
 */
package com.intermacs.commons.dtos;

import java.io.Serializable;
import java.util.HashMap;

import com.intermacs.commons.enums.EParametro;
import com.intermacs.commons.enums.IParametro;


/**
 * @author ENUNEZT
 * 
 */
@SuppressWarnings("serial")
public class RequestDTO extends BaseRequestResponseDTO implements Serializable {
	private boolean flushMode;
	private int operacion;
	
	public static final int OPERACION_0=0;
	public static final int OPERACION_1=1;
	public static final int OPERACION_2=2;
	public static final int OPERACION_3=3;
	public static final int OPERACION_4=4;
	public static final int OPERACION_5=5;
	/**
	 * Retorna nueva instacia 
	 * @return
	 */
	public RequestDTO getInstacia(){
		return new RequestDTO();
	}
	/**
	 * Retorna nueva instancia incluyendo parametros
	 * @return
	 */
	public RequestDTO getInstaciaParametros(){
		
		RequestDTO _req=new RequestDTO();
		_req.param=this.param;
		return _req;
	}
	
	public RequestDTO() {
		super();
		param = new HashMap<IParametro, Object>();
	}

	
	public RequestDTO(PaginationDTO paginationDTO) {
		super();	
		param = new HashMap<IParametro, Object>();
		this.pagination=true;
		setParam(EParametro.Paginable, paginationDTO);
	}

	
	/**
	 * @return the pagination
	 */


	public boolean isFlushMode() {
		return flushMode;
	}

	public void setFlushMode(boolean flushMode) {
		this.flushMode = flushMode;
	}

	/**
	 * @return the operacion
	 */
	public int getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}

}
