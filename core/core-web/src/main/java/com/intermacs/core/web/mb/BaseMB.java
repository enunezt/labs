package com.intermacs.core.web.mb;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ReferencedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.core.common.constantes.IKeyMsg;
import com.intermacs.security.ContextoPrincipal;
import com.intermacs.utils.BundleUtils;



@ReferencedBean
public class BaseMB {
	 //public static final String PUSH_CDI_TOPIC = "pushCdi";
		@Inject
		private Logger log;
		
		
		protected final String outcome ="/login.jsf"; // Do your thing?
		@SuppressWarnings("el-syntax")
		protected final String ABRIR_EXPR="#{";
		
		protected final String CERRAR_EXPR="}";
		protected final String USER_INVITADO="Invitado";
		
		@Inject
	    protected FacesContext facesContext;
		
		private static Map<String, String> mapPublicURL;
		
	public BaseMB() {
		
	}
	
	@PostConstruct
	private void verificarContextoSesion(){
		preVerificarContextoSesion();
		if(mapPublicURL==null){
			mapPublicURL=new TreeMap<String, String>();
			mapPublicURL.put("/index.xhtml", "/index.xhtml");	
			mapPublicURL.put("/index.jsf", "/index.jsf");
			mapPublicURL.put("/index.html", "/index.html");
			
			mapPublicURL.put("/login.xhtml", "/login.xhtml");	
			mapPublicURL.put("/login.jsf", "/login.jsf");
			
			mapPublicURL.put("/recuperarLoguin.xhtml", "/recuperarLoguin.xhtml");
			mapPublicURL.put("/recuperarLoguin.jsf", "/recuperarLoguin.jsf");
			
			mapPublicURL.put("/accessDenied.xhtml", "/accessDenied.xhtml");
			mapPublicURL.put("/accessDenied.jsf", "/accessDenied.jsf");
			
			mapPublicURL.put("/error/viewExpired.xhtml", "/error/viewExpired.xhtml");
			mapPublicURL.put("/error/viewExpired.jsf", "/error/viewExpired.jsf");
			
			mapPublicURL.put("/error/error.xhtml", "/error/error.xhtml");
			mapPublicURL.put("/error/error.jsf", "/error/error.jsf");
		}
		
	
		/*if(context==null && facesContext!=null){
		    
		    addInfoMessage("Sesión Expirada","Ingrese al sistema con su usuario y contraseña");
		    ExternalContext ctx = facesContext.getExternalContext();
		    String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
		    String ipLocal=((ServletRequest) ctx.getRequest()).getLocalAddr();
		    ipLocal=ipLocal+" - " +((ServletRequest) ctx.getRequest()).getRemoteAddr();
		   ipLocal=ipLocal+" - " +((ServletRequest) ctx.getRequest()).getLocalName();
		   ipLocal=ipLocal+" - " +((ServletRequest) ctx.getRequest()).getRemoteHost();
		   String view=ctx.getRequestServletPath();
		   
		    try {
		          
			   if(!outcome.equals(view) &&  !mapPublicURL.containsKey(view)
					   && !(getPublicURLs()!=null && !getPublicURLs().containsKey(view))){
			    ctx.redirect(ctxPath);
			   } 
			} catch (IOException e) {
				getLogger().severe(e.getMessage());
			}
		        catch (IllegalStateException e) {
		        	getLogger().severe(e.toString() +" -> Se intentó ingresar ilegalmenta a :"+view+" "+ipLocal);
				}
		}*/
	    
	}
	/**
	 * Sobre escribir si se considera pertinente
	 */
	protected void preVerificarContextoSesion(){
		
		
	}
	
	
	protected  Map<String, String> getPublicURLs() {
		return null;
	}
	
	/**
	 * Llamdo cuando se ordena cerrar sesion
	 */
	public String logout(int a) {
	  /*  ExternalContext ctx = facesContext.getExternalContext();
	    String ctxPath = 
	        ((ServletContext) ctx.getContext()).getContextPath();

	    try {
	      // Usar el contexto de JSF para invalidar la sesión,
	      // NO EL DE SERVLETS (nada de HttpServletRequest)
	      ((HttpSession) ctx.getSession(false)).invalidate();

	      // Redirección de nuevo con el contexto de JSF,
	      // si se usa una HttpServletResponse fallará.
	      // Sin embargo, como ya está fuera del ciclo de vida 
	      // de JSF se debe usar la ruta completa -_-U
	      ctx.redirect(ctxPath + outcome);
	    } catch (IOException ex) {
	    	getLogger().severe(ex.getMessage());
	    }*/
	    return "index.jsf";
	  }
	
	/**
	 * Add un mensaje de error  to a sepcific client.
	 *
	 * @param bundle the information message
	 */
	public  void addErrorMessage(ResourceBundle bundle,String key,Object... params) {			
		String value=bundle.getString(key);
		String msg=BundleUtils.getString(value, params);
		String header=bundle.getString(IKeyMsg.MSG_HEADER_ERROR);
		facesContext.addMessage(null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR, msgSummary(header,msg), msg));
	}
	
	
protected String getMensaje(ResourceBundle bundle,String key,Object... params){
	
	String value=bundle.getString(key);
	return BundleUtils.getString(value, params);
}
	
	 /**
	 * Add un mensaje de error  to a sepcific client.
	 * 
	 * @param msg the information message
	 */
	public  void addErrorMessage(ResourceBundle bundle,String key) {			
		String value=bundle.getString(key);
		String header=bundle.getString(IKeyMsg.MSG_HEADER_ERROR);
		 facesContext.addMessage(null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR, msgSummary(header,value), value));
	}
	
	  /**
     * Añade un mensaje de error a la jeraquia de componetes de la página JSF
     * @param mensaje
     */
   
    public  void addErrorMessage(String msg,String msgDetalle) {
		 facesContext.addMessage(null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgDetalle));
	}
    
    /**
     * Añade un mensaje de error a la jeraquia de componetes de la página JSF
     * @param mensaje
     */
   
    public  void addErrorMessage(Exception e) {
    	
    	 String errorMessage = getRootErrorMessage(e);
    	 if(errorMessage==null){
    		 errorMessage=e.toString();
    	 }
    	 
         FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el sistema.", errorMessage);
         facesContext.addMessage(null, m);
       if(  getLogger().isLoggable(Level.SEVERE))
         getLogger().log(Level.SEVERE, errorMessage, e);
	}
    
    
    
    /**
	 * Add information message to a sepcific client.
	 * 
	 * @param msg the information message
	 */
	public  void addInfoMessage(String msg,String msgDetalle) {
		 facesContext.addMessage(null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msgDetalle));
	}
	
	/**
	 * Add information message to a sepcific client.
	 * 
	 * @param msg the information message
	 */
	public  void addWarnMessage(String msg,String msgDetalle) {
		 facesContext.addMessage(null,
                 new FacesMessage(FacesMessage.SEVERITY_WARN, msg,msgDetalle));
	}
	
	
	 /**
		 * Add information message to a sepcific client.
		 * 
		 * @param msg the information message
		 */
		public  void addInfoMessage(ResourceBundle bundle,String key) {			
			String value=bundle.getString(key);
			String header=bundle.getString(IKeyMsg.MSG_HEADER_INFO);
			 facesContext.addMessage(null,
	                 new FacesMessage(FacesMessage.SEVERITY_INFO, msgSummary(header,value), value));
		}
		
		 /**
		 * Add information message to a sepcific client.
		 * 
		 * @param msg the information message
		 */
		public  void addWarnMessage(ResourceBundle bundle,String key) {			
			String value=bundle.getString(key);
			String header=bundle.getString(IKeyMsg.MSG_HEADER_INFO);
			 facesContext.addMessage(null,
	                 new FacesMessage(FacesMessage.SEVERITY_WARN, msgSummary(header,value), value));
		}
		
		/**
		 * Add information message to a sepcific client.
		 * 
		 * @param msg the information message
		 */
		public  void addInfoMessage(ResourceBundle bundle,String key,Object... params) {			
			String value=bundle.getString(key);
			String msg=BundleUtils.getString(value, params);
			String header=bundle.getString(IKeyMsg.MSG_HEADER_INFO);
			facesContext.addMessage(null,
	                 new FacesMessage(FacesMessage.SEVERITY_INFO, msgSummary(header,msg), msg));
		}
		
		
		 /**
		  * Determina el mensaje a presentar de acuerdo 
		  * al listado recibido
		  * @param list
		  */
	   protected void presentarMensajeConsulta(ResourceBundle bundle,Collection<?> list){
		   if(list!=null && !list.isEmpty()){
			   String param=""+list.size() ;
	 		     addInfoMessage(bundle,IKeyMsg.MSG_CONSULTA_EXITOSA,param);
		   }else{		   
			   addInfoMessage(bundle,IKeyMsg.MSG_CONSULTA_SIN_REGISTROS);	   
		   }
		   }


	/**
	 * Determina el mensaje a presentar de acuerdo
	 * al listado recibido
	 *
	 * @param object
	 */
	protected void presentarMensajeConsultaUnico(ResourceBundle bundle, Object object) {
		if (object != null) {
			addInfoMessage(bundle, IKeyMsg.MSG_CONSULTA_EXITOSA, 1);
		} else {
			addInfoMessage(bundle, IKeyMsg.MSG_CONSULTA_SIN_REGISTROS);
		}
	}
	

    protected String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
    
    /**
     *  creating value expression with the help of the expression factory and the ELContext
     * @param expressionAtt
     * @param value
     */
    public void setValueExpression(String expressionAtt,byte[] value){
   	 Application app = FacesContext.getCurrentInstance().getApplication();		 
   	 
   	 ExpressionFactory exprFactory = app.getExpressionFactory();
   	// getting the ELContext from faces context
   	 ELContext elContext = FacesContext.getCurrentInstance().getELContext();
   	 // creating value expression with the help of the expression factory and the ELContext

   	 ValueExpression valExpr = exprFactory.createValueExpression(elContext, "#{"+ expressionAtt+"}",value.getClass() );
   	 valExpr.setValue(elContext,value);
   	 

   	 
    }
  
    public static String downloadFile( byte[] imageInByte/*String url*/) {
        FileOutputStream file = null;
        String filePath = "";
        try {
              //  URL p = new URL(url);
                
                InputStream in = new ByteArrayInputStream(imageInByte);

                BufferedInputStream bin = new BufferedInputStream(in);

                filePath = System.getProperty("java.io.tmpdir") + "imgWebApp";
                System.out.println("Ruta Archivo:-->"+filePath);
                String _filePath = filePath.replaceAll("/", File.separator + File.separator);
                File f = new File(_filePath);
                f.getParentFile().mkdirs();

                file = new FileOutputStream(f);
                BufferedOutputStream out = new BufferedOutputStream(file);

                for (int b; (b = bin.read()) != -1; ) {
                        out.write(b);
                }

                out.flush();
                file.close();
                bin.close();

                return _filePath;
        } catch (Exception e) {
                throw new RuntimeException("Download file failed.  " + e.getMessage(), e);
        }
}
    
 private String msgSummary(String header,String msg){
	 
	 return header+" :\n "+msg;
	 
 }
	
    public ContextoPrincipal getContext() {
    	HttpServletRequest request = 
				(HttpServletRequest)facesContext
						.getExternalContext()
							.getRequest();
    	
    	if(request.getUserPrincipal() instanceof ContextoPrincipal){
    		return (ContextoPrincipal) request.getUserPrincipal();	
    	} else {
    	try {
			request.login(USER_INVITADO, USER_INVITADO);
			return (ContextoPrincipal) request.getUserPrincipal();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
    	
    	}
	}

	protected Logger getLogger() {
	return	log;
	}
/**
 * Verifica y presenta mensajes de error si los hay
 * @param response
 */
	protected void verificarMsgServicio(ResponseDTO response){
		
		
		if(response.getMensajes()!=null && response.getMensajes().length>0){
			
			for (int i = 0; i < response.getMensajes().length; i++) {
				if(response.getMensajes()[i].isTipoError())
				addErrorMessage("Mensaje del sistema:", response.getMensajes()[i].getMensaje()); 
				else if(response.getMensajes()[i].isTipoInfo())
						addInfoMessage("Mensaje del sistema:", response.getMensajes()[i].getMensaje());
				else if(response.getMensajes()[i].isTipoWarn())
					addWarnMessage("Mensaje del sistema:", response.getMensajes()[i].getMensaje()); 
			}
			
			
		}
	}
	
	public boolean hasOpcion(Long... idOpcion){
		ContextoPrincipal  context=getContext();
		if(context!=null){
			for (int i = 0; i < idOpcion.length; i++) {
				if(context.hasRol(idOpcion[i].longValue())/*containsUserOpcion(idOpcion[i].longValue())*/){
					
					return true;
				}
			}
			
		
		}
		return false;
	}
	
public boolean hasPerfil(Integer... idPerfil){
		
		/*if(context!=null){
			for (int i = 0; i < idPerfil.length; i++) {
				if(context.containsPerfilUser(idPerfil[i].longValue())){
					
					return true;
				}
			}
			
		
		}*/
		return false;
	}
	
	
	
	
public boolean hasOpcion(String opciones){
	ContextoPrincipal  context=getContext();
		if(context!=null){
			String arr[]=opciones.split(",");
			
			for (int i = 0; i < arr.length; i++) {
				if(context.hasRol(Long.valueOf(arr[i]))){ 
					
					return true;
				}
			}
			
		
		}
		return false;
	}

public void doSimplePostProcessXLS(Object document) {  
	  HSSFWorkbook wb = (HSSFWorkbook) document;  
	    HSSFSheet sheet = wb.getSheetAt(0); 	      
	    int rowIndex = sheet.getLastRowNum()== -1 ? 0 : sheet.getLastRowNum();		
	 		 sheet.shiftRows(0, rowIndex, 2);
	  
		 HSSFRow row1= sheet.createRow(0); 
		 HSSFCell cell1= row1.createCell(1);
		 cell1.setCellValue(getTituloReporte()); 
	   
	    HSSFCellStyle cellStyle = wb.createCellStyle();    
	    cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);  
	    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	    HSSFRow header = sheet.getRow(2); 
	    for(int i=0; i < header.getPhysicalNumberOfCells();i++) {  
	        HSSFCell cell = header.getCell(i);  
	          
	        cell.setCellStyle(cellStyle);  
	    }  
	}

/**
 * Retorna el Titulo del Reportes
 * @return
 */
protected String getTituloReporte(){
	 return "Reporte "; 
}

}