/**
 * 
 */
package com.intermacs.core.enums;

import com.intermacs.commons.enums.IParametro;


/**
 * @author enunezt
 *
 */
public enum EParametrosAdmin implements IParametro {
	
	RolesDisponiblesUser ("PerfilesDisponiblesUser"),
    RolesUser ("PerfilesUser"),
    User ("User");//, OTRO(),....;

    private final String name; // in meters
    EParametrosAdmin(String name) {
   
        this.name = name;
    }

	@Override
	public String getName() {
		return this.name;
	}

}
