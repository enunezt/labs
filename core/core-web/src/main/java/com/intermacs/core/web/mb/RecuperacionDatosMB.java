package com.intermacs.core.web.mb;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.intermacs.commons.constants.ICatalogo;
import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.common.constantes.IKeyMsg;
import com.intermacs.core.facade.CatalogoFacade;
import com.intermacs.core.facade.UsuarioFacade;
import com.intermacs.core.model.entidades.CatalogoDet;
import com.intermacs.core.model.entidades.Usuario;
import com.intermacs.exceptions.ServicioFacadeExcepcion;






/**
 * User settings.
 *
 * @author 
 * @version 
 */
@ManagedBean (name ="recuperacionDatosMB")
@ViewScoped
public class RecuperacionDatosMB extends BaseMB{
	@Inject
	private Logger log;
		
	@Inject
    private UsuarioFacade usuarioServicio;
	
	@Inject
	private CatalogoFacade catalogoService;
	
	@ManagedProperty("#{msgs}")
    private ResourceBundle bundle; 
	
	
	private List<CatalogoDet> tipoDocumentoItems;
	
	private Usuario usuarioFiltro;	

	public RecuperacionDatosMB() {
		
		 usuarioFiltro=new Usuario();
	}
	
	
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	
	
	public String enviarSolicitudRecuperacionDatos(){
		
		 RequestDTO requestDTO=new RequestDTO();
		    requestDTO.setObject(usuarioFiltro);  
		    
		    try {
				ResponseDTO resp=usuarioServicio.recuperarDatoLoguinUsuario(requestDTO);
				if(resp.isExisteResultado()){
					 addInfoMessage(bundle,IKeyMsg.MSG_EXITO_RECUPERAR_DATOS_LOGUIN);
				}else{
					 addInfoMessage(bundle,IKeyMsg.MSG_CONSULTA_SIN_REGISTROS);
				}
				
			} catch (ServicioFacadeExcepcion e) {
				if(log.isLoggable(Level.FINE)) 
				log.log(Level.FINE, "Error en enviarSolicitudRecuperacionDatos", e);
				addErrorMessage(e);
			}
		
		return "";// "/login.xhtml?faces-redirect=true";
	}
	
	/**
	 * @return the tipoDocumentoItems
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogoDet> getTipoDocumentoItems() {
		if(tipoDocumentoItems==null){
	    RequestDTO req = new RequestDTO();
	    req.setParam(EParametro.CATALOGO, ICatalogo.TIPOS_DOCUMENTO);
	    try {
		ResponseDTO resp = catalogoService
			.consultarLstCatalogDetalle(req);
		tipoDocumentoItems = (List<CatalogoDet>) resp
			.getParam(EParametro.ResultList);
	    } catch (ServicioFacadeExcepcion e) {
		addErrorMessage(e);
	    }}

	return tipoDocumentoItems;
	}


	public Usuario getUsuarioFiltro() {
		return usuarioFiltro;
	}


	public void setUsuarioFiltro(Usuario usuarioFiltro) {
		this.usuarioFiltro = usuarioFiltro;
	}
	


}