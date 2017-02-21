package com.intermacs.security;
import java.security.Principal;
import java.util.Set;

import javax.security.auth.Subject;
 
public class ContextoPrincipal implements Principal {
 
    private String _name = null;
    private Long idUsuario = null;
    private Long ipUsuario = null;
	private Subject _subject;
	
	private Object _objectUser;
	private Object menuUser;
        
    public Object getMenuUser() {
		return menuUser;
	}
	public void setMenuUser(Object menuUser) {
		this.menuUser = menuUser;
	}
	public ContextoPrincipal(Subject subject,String name, Long idUsuario,Object objectUser) {
        _name = name;
       _subject=subject;
       _objectUser=objectUser;
       this.idUsuario=idUsuario;
    }
    public void printPrincipals(){
    Set<Principal>	_lst=_subject.getPrincipals();
    for (Principal principal : _lst) {
		System.out.println(principal.getName()); 
	}
    }
    
 
    public ContextoPrincipal(String name) {
        _name = name;
    }
    
    
    public Object getUserLogin(){
    	
    	return _objectUser;
    }
    

 	public Long getIpUsuario() {
 		return ipUsuario;
 	}
 
    
    public boolean equals(Object another) {
    	
    	//if(another instanceof ContextoPrincipal ){
    		 return ((ContextoPrincipal)another).getName().equals(_name);
    	//}else {
    		//return false;
    	//}
    	
       
    }
    public boolean hasRol(Long idRol){
    	
    	return _subject.getPrincipals().contains(new ContextoPrincipal(""+idRol));
    }
 
 
    public String getName() {
        return _name;
    }
 
 
    public int hashCode() {
        return _name.hashCode();
    }
 
 
    public String toString() {
        return "[SamplePrincipal] : " + _name;
    }
    
    public Long getIdUsuario() {
		return idUsuario;
	}

 
}