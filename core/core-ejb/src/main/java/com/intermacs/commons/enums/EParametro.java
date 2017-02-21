/**
 * 
 */
package com.intermacs.commons.enums;



/**
 * @author enunezt
 *
 */
public enum EParametro implements IParametro {
	
	/**
	 * Entidad base para la parte génrica
	 */
	OBJECT ("EParametro.Object"),
	ID_OBJECT("EParametro.Idobject"),
	NamedQueryLocal("EParametro.namedQueryLocal"),
	Nombre("EParametro.nombre"),
	/**
	 * Entidad paginadora
	 */
	Paginable ("EParametro.EntidadPaginadora"),
	PREDICADO("EParametro.PREDICADO"),	
	NamedQuery("EParametro.namedQuery"),
	ResultList("EParametro.ResultList"),
	ParemtrosQuery("EParametro.ParemtrosQuery"),
	Map("EParametro.MapaObjetos"),
	Set("EParametro.SetObjetos"),
	User("EParametro.EntidadUsuario"),
	CATALOGO_DET("EParametro.EntidadCatalogoDet"),
	CATALOGO("EParametro.EntidadCatalogo"),
	Imagen("EParametro.EntidadImagen"),
	ENT_PERSONA("EParametro.EntidadPersona"),
	IdEntidad("EParametro.IdEntidad"),
	ImagenTipo("EParametro.ImagenTipo"),
	ClassEntidad("EParametro.ClassEntidad"),
	FlushMode("EParametro.FlushMode"),
	FECHA_CONSULTA("EParametro.AnioConsulta"),
	FECHA_DESDE("EParametro.FechaDesde"),
	FECHA_HASTA("EParametro.FechaHasta"),
	ANIO("EParametro.Anio"),
	PROGRAMA("EParametro.PROGRAMA"),
	LIMIT_SELECT("EParametro.PROGRAMA"),	
	Entidad("EParametro.object"),
	NombreSeq("EParametro.nombreSeq"),
	ESTADO("EParametro.ESTADO"),
	PARAM_1("EParametro.Param_1"),
	PARAM_2("EParametro.Param_2"),
	PARAM_3("EParametro.Param_3"),
	PARAM_4("EParametro.Param_4"),
	PARAM_5("EParametro.Param_5")
	/*,
	ListaParametrosQueryConIN("ListaParametrosQueryConIN")*/;//, OTRO(),....;

    private final String name; // in meters
    EParametro(String name) {
   
        this.name = name;
    }

	@Override
	public String getName() {
		return this.name;
	}


}
