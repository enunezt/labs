package com.intermacs.commons.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Documented
public @interface ServicioFacade {
	/**
	 * Método para marcar la descripción de la clase
	 * @return
	 */
	public String descripcion();
	/**
	 *Método para marcar el nombre de la clase 
	 * @return
	 */
	public String clase();

}
