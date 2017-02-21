package com.intermacs.core.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intermacs.core.model.entidades.CatalogoDet;




public class FacesUtils {
	/**
	 * Get servlet context.
	 *
	 * @return the servlet context
	 */
	public static ServletContext getServletContext() {
		return (ServletContext)getCurrentInstancia().getExternalContext().getContext();
	}

    /*public static PortletContext getPortletContext() {
        return (PortletContext)getCurrentInstancia().getExternalContext().getContext();
    }

    public static ExternalContext getExternalCpntext() {
		return getCurrentInstancia().getExternalContext();
	}*/

	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) getCurrentInstancia()
				.getExternalContext().getResponse();
	}


	
	
	/**
	 * Store the managed bean inside the session scope.
	 * 
	 * @param beanName the name of the managed bean to be stored
	 * @param managedBean the managed bean to be stored
	 */
	public static void setManagedBeanInSession(String beanName, Object managedBean) {
		getCurrentInstancia().getExternalContext().getSessionMap().put(beanName, managedBean);
	}
	
	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name the name of the parameter
	 * @return the parameter value
	 */
	public static String getRequestParameter(String name) {
		return (String)getCurrentInstancia().getExternalContext().getRequestParameterMap().get(name);
	}
	
	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name the name of the parameter
	 * @return the parameter value
	 */
	public static void addRequestParameter(String key, String value) {
	getCurrentInstancia().getExternalContext().getRequestParameterMap().put(key, value);
	}
	

	/**
	 * Get parameter value from  flash request scope.
	 * 
	 * @param name the name of the parameter
	 * @return the parameter value
	 */
	public static Object getScopeFlashParameter(String name) {
		return getCurrentInstancia().getExternalContext().getFlash().get(name);
	}
	
/**
 * set parameter value from  flash request scope.
 * @param name
 * @param value
 */
	public static void setScopeFlashParameter(String name, Object value) {
		getCurrentInstancia().getExternalContext().getFlash().put(name,value);
	}

	/**
	 * remove parameter value from  flash request scope.
	 * @param name
	 * @return object to remove
	 */
		public static Object removeScopeFlashParameter(String name) {
			return getCurrentInstancia().getExternalContext().getFlash().remove(name); 
		}

	
	/**
	 * Add information message.
	 * 
	 * @param msg the information message
	 */
	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}
	
	/**
	 * Add information message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 */
	public static void addInfoMessage(String clientId, String msg) {
		getCurrentInstancia().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	/**
	 * Add error message.
	 * 
	 * @param msg the error message
	 */
	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}
	
	/**
	 * Add error message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the error message
	 */	
	public static void addErrorMessage(String clientId, String msg) {
		getCurrentInstancia().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
	
	
	
	public static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory)FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication(); 
	}
	
	public static ValueExpression getValueBinding(String el, Class<?> clazz) {		
 Application app = FacesUtils.getApplication();	    	 
     	 ExpressionFactory exprFactory = app.getExpressionFactory();
     	// getting the ELContext from faces context
     	
     	 ELContext elContext = getCurrentInstancia().getELContext();
     	 // creating value expression with the help of the expression factory and the ELContext
      	return  exprFactory.createValueExpression(elContext, el,clazz);
	}
	
	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest)getCurrentInstancia().getExternalContext().getRequest();
	}
	
	public static Object getElValue(String elExpression, Class<?> clazz) {
			return getValueBinding(elExpression,clazz).getValue(getCurrentInstancia().getELContext());
	}
	

    public static String getMessageByKey(String key) {
        String messageBundleName = getCurrentInstancia().getApplication().getMessageBundle();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(messageBundleName);

        try {
            return resourceBundle.getString(key);
        } catch (Exception e) {
            return key;
        }

    }
    
    public static void setSessionAttribute(String name,Object arg){    	
    	FacesContext ctx = getCurrentInstancia();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		request.getSession().setAttribute(name, arg);    	
    }
    public static Object getSessionAttribute(String name){    	
    	FacesContext ctx = getCurrentInstancia();
    	if(ctx==null)
    		return null;
    	
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		return request.getSession().getAttribute(name);    	
    }
    
    public static Object getRequestAttribute(String name){    	
    	FacesContext ctx = getCurrentInstancia();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		return request.getAttribute(name);    	
    }
    
    public static void setRequestAttribute(String name,Object arg){    	
    	FacesContext ctx = getCurrentInstancia();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
	request.setAttribute(name,arg);    	
    }
    public static void removeSessionAttribute(String name){    	
    	FacesContext ctx = getCurrentInstancia();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		request.getSession().removeAttribute(name);    	
    }
    
    public static FacesContext getCurrentInstancia(){
    return	FacesContext.getCurrentInstance();    	
    }
    /**
     * retorna el listado de select items apartir del listado de catalgo
     * @param list
     * @return
     */
    public static List<SelectItem> getListSelectItems(List<CatalogoDet> list, final Map<Long,CatalogoDet> map){
    	List<SelectItem> retorno=null;
    	if(list!=null){
    		retorno=new ArrayList<SelectItem>();
    	for (CatalogoDet CatalogoDet : list) {
    		retorno.add(new SelectItem(CatalogoDet.getIdCatalogoDet(), CatalogoDet.getLabelValue(), CatalogoDet.getDescripcion()));
    		if(map!=null){
    			map.put(CatalogoDet.getIdCatalogoDet(), CatalogoDet);
    			}
    		}
    		}
    	return retorno;
    }
    
    public static SelectItem[] getArraySelectItems(List<CatalogoDet> list , final Map<Long,CatalogoDet> map){
    SelectItem[] retorno=null;
    List<SelectItem> _list=getListSelectItems(list,map);
    if(_list!=null){
    	retorno=_list.toArray(new SelectItem[_list.size()]);
    }   	
    	return retorno;
    }
    
    
    public static String getPath(String aPosibleRuta)
    {
    	/*ServletContext application = FacesUtils.getServletContext();
        String disenoReporte = application.getRealPath(aPosibleRuta);
        */
      String path = ""; 
      HttpServletRequest request = getServletRequest();
      path = request.getSession().getServletContext().getRealPath(aPosibleRuta);
      return path;
    }
    
    
}
