package com.intermacs.core.services;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.intermacs.commons.dtos.RequestDTO;
import com.intermacs.commons.dtos.ResponseDTO;
import com.intermacs.commons.enums.EParametro;
import com.intermacs.core.model.entidades.EntidadAuditada;
import com.intermacs.core.model.entidades.Usuario;
import com.intermacs.exceptions.ServicioExcepcion;
import com.intermacs.security.ContextoPrincipal;
import com.intermacs.utils.DateUtils;


/**
 * EJB generico que implementa las opciones de altas, bajas, modificaciones y acceso por clave
 * para cualquier tipo de Entidad
 *
 * @param
 * @author enunezt
 */


@SuppressWarnings("unchecked")
public abstract class GenericoServices  {

    protected static final String PRE_PARAM = "p_";
    @SuppressWarnings("rawtypes")
    private Map parameters;
    // @PersistenceContext(unitName = "EjemploTiendaPU")


    //Logger log;

    /**
     * Retorna el EntityManager indicado para la entidad
     *
     * @return
     */
    protected abstract EntityManager getEntityManager();
    
    protected abstract ContextoPrincipal getContextoPrincipal();
    protected  Usuario getUserLogin(){
    	
    return 	(Usuario) getContextoPrincipal().getUserLogin();
    }

   
    protected ResponseDTO crear(RequestDTO request) throws ServicioExcepcion {
        ResponseDTO response = new ResponseDTO();
        addDatosAuditoriaCrear(request);
        Object ent = getobject(request);
        try {

            getEntityManager().persist(ent); // Crea una nueva tupla en la BD con los datos de "entidad"
            // -> ademas genera su ID
            if (request.isFlushMode())
                getEntityManager().flush();
        } catch (Exception e) {
            //  e.printStackTrace();
            throw new ServicioExcepcion(e);
        }

        request.setObject(ent);
        response = new ResponseDTO(ent);
        return response;
    }

    
    protected ResponseDTO actualizar(RequestDTO request) throws ServicioExcepcion {
        addDatosAuditoriaModificar(request);
        Object ent = getobject(request);
        // Actualiza los datos de "entidad" en su correspondiente tupla BD
        ResponseDTO response = new ResponseDTO(getEntityManager().merge(ent));
        if (request.isFlushMode())
            getEntityManager().flush();

        return response;
    }


    
    protected void eliminar(RequestDTO request) throws ServicioExcepcion {
        getEntityManager().remove(getEntityManager().merge(getobject(request)));  // Actualiza y elimina

        if (request.isFlushMode())
            getEntityManager().flush();
    }

    /**
     * Se debe pasar en el request los parametros EParametro.Idobject y EParametro.ClassEntidad
     * Idobject=id de la entidad local del dao,el atributo @id de la entidad se debe llamar "id" +NombreEntidad
     * ClassEntidad=Class de la entidad que se va a consultar
     * * @param id RequestDTO
     *
     * @return Entidad
     * @throws ServicioExcepcion
     */
    
    protected Object buscarPorId(RequestDTO id) throws ServicioExcepcion {
        @SuppressWarnings({"rawtypes"})
        Class claseEntidad = (Class) id.getParam(EParametro.ClassEntidad);   // Identifica la clase real de las entidades gestionada por este objeto (T.class)
        Object idEntidad = id.getParam(EParametro.ID_OBJECT);
        return getEntityManager().find(claseEntidad, idEntidad);
    }

    /**
     * Se debe pasar en el request los parametros EParametro.NamedQueryLocal, EParametro.Idobject y EParametro.ClassEntidad
     * NamedQueryLocal=query de la entidad local del dao ej: "Imagen.findById", query = "SELECT i FROM Imagen i WHERE i.idImagen = :idImagen"
     * Idobject=id de la entidad local del dao,el atributo @id de la entidad se debe llamar "id" +NombreEntidad
     * ClassEntidad=Class de la entidad que se va a consultar
     *
     * @param request
     * @return
     * @throws ServicioExcepcion
     */
    @SuppressWarnings({"rawtypes"})
    protected Object buscarPorIdNamedQeryId(RequestDTO request) throws ServicioExcepcion {
        String namedQuery = (String) request.getParam(EParametro.NamedQueryLocal);
        Object id = request.getParam(EParametro.ID_OBJECT);
        Query query = getEntityManager().createNamedQuery(namedQuery);
        Class claseEntidad = (Class) request.getParam(EParametro.ClassEntidad);
        query.setParameter("id" + claseEntidad.getSimpleName(), id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    /**
     * Se debe pasar el parametro EParametro.NamedQuery y opcional el parametro EParametro.ParemtrosQuery y EParametro.LIMIT_SELECT
     * NamedQuery=valor del namedQuery  declarado en las entidades
     * LIMIT_SELECT=numero maximo de registros a retornar
     * ParemtrosQuery= es opcional y corresponde a los parameros con sus repectivos valores para ser
     * utiliza en el query.
     * Adicionalemente este método controla la paginación
     *
     * @param queryName RequestDTO
     * @return
     * @throws ServicioExcepcion
     */
    @SuppressWarnings({"rawtypes"})    
    protected ResponseDTO findWithNamedQuery(RequestDTO queryName) throws ServicioExcepcion {
              List<?> list = findWithNamedQueryEntityList(queryName);
              ResponseDTO response = new ResponseDTO();
        response.setParam(EParametro.ResultList, list);

        return response;
    }



    /**
     * Se debe pasar el parametro EParametro.NamedQuery y opcional el parametro EParametro.ParemtrosQuery y EParametro.LIMIT_SELECT
     * NamedQuery=valor del namedQuery  declarado en las entidades
     * LIMIT_SELECT=numero maximo de registros a retornar
     * ParemtrosQuery= es opcional y corresponde a los parameros con sus repectivos valores para ser
     * utiliza en el query.
     * Adicionalemente este método controla la paginación
     *
     * @param queryName RequestDTO
     * @return List<Object>
     * @throws ServicioExcepcion
     */

    protected List<?> findWithNamedQueryEntityList(RequestDTO queryName) throws ServicioExcepcion {
        //List<Object>
        String namedQueryName = (String) queryName.getParam(EParametro.NamedQuery);
        Integer _limitSelect = (Integer) queryName.getParam(EParametro.LIMIT_SELECT);
        
        Map parameters = (Map) queryName.getParam(EParametro.ParemtrosQuery);  //    
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        if (parameters != null) {
            Set<Entry> rawParameters = parameters.entrySet();
            for (Entry entry : rawParameters) {

                query.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        List<?> list = verificarPaginacion(query, queryName,_limitSelect);


        return list;
    }
/**
 * Busqueda de entidades de acuerdo al predicado definido  
 * <br/>
 * request.setParam(EParametro.PREDICADO,Predicate[]);
 * 
 * @param request con:<br/>
 * request.setParam(EParametro.PREDICADO,Predicate[]);
 * <br/>
 * request.setObject(EntidadGestionada);
 * <br/>
 * @return  <br/>
 * ResponseDTO.getResultList
 * @throws ServicioExcepcion
 */
    protected <T> ResponseDTO findWithPredicate( Class<T> entityClass,Predicate[] predicate) throws ServicioExcepcion {
           
              ResponseDTO response = new ResponseDTO();       
             
              CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

              // Populate pageItems

              CriteriaQuery<T> criteria = builder.createQuery(entityClass);
              Root<T> root = criteria.from(entityClass);
              TypedQuery<T> query = getEntityManager().createQuery(criteria.select(root).where(predicate));
             // query.setFirstResult(page * pageSize).setMaxResults(pageSize);
             // List<T> pageItems = query.getResultList();
              response.setParam(EParametro.ResultList, query.getResultList());
        return response;
    }
    
   


    /**
     * Se debe pasar el parametro EParametro.NamedQuery y opcional el parametro EParametro.ParemtrosQuery
     * NamedQuery=valor del namedQuery  declarado en las entidades
     * ParemtrosQuery= es opcional y corresponde a los parameros con sus repectivos valores para ser
     * utiliza en el query.
     * Adicionalemente este método controla la paginación
     *
     * @param queryName RequestDTO
     * @return
     * @throws ServicioExcepcion
     */
    @SuppressWarnings({"rawtypes"})

    protected ResponseDTO findWithNamedQuerySingleResult(RequestDTO queryName) throws ServicioExcepcion, NoResultException, NonUniqueResultException {
        //List<Object>
		String namedQueryName = (String) queryName.getParam(EParametro.NamedQuery);
		Map parameters = (Map) queryName.getParam(EParametro.ParemtrosQuery);
		ResponseDTO response = new ResponseDTO();
		try {
			Query query = getEntityManager().createNamedQuery(namedQueryName);

			if (parameters != null) {
				Set<Map.Entry> rawParameters = parameters.entrySet();
				for (Entry entry : rawParameters) {
					//System.out.println(entry.getKey().toString() + ":"+entry.getValue());
					query.setParameter(entry.getKey().toString(),entry.getValue());
				}
			}
			response.setObject(query.getSingleResult());
			response.setExisteResultado(true);

		} catch (NoResultException nRex) {
			/*throw new ServicioExcepcion(
					"No fue encontrado ningun Registro en la Base de Datos.");*/
		
		} catch (NonUniqueResultException nUniex) {
			throw new ServicioExcepcion(
					"Existen mas de un Registro en la base de Datos");
		}
		return response;
    }


    protected Object getNextValSecuencia(RequestDTO queryName) throws ServicioExcepcion {

        String namedQueryName = "select nextval('" + queryName.getParam(EParametro.NombreSeq).toString() + "')";
        Query query = getEntityManager().createNativeQuery(namedQueryName);
        Object nexVal = query.getSingleResult();

        return nexVal;
    }

    /**
     * Se debe pasar el parametro EParametro.NamedQuery y opcional el parametro EParametro.ParemtrosQuery
     * NamedQuery=valor del namedQuery  declarado en las entidades
     * ParemtrosQuery= es opcional y corresponde a los parameros con sus repectivos valores para ser
     * utiliza en el query.
     * Adicionalemente este método controla la paginación
     *
     * @param queryName RequestDTO
     * @return
     * @throws ServicioExcepcion
     */
    protected Object findWithNamedQuerySingleResultEntity(RequestDTO queryName) throws ServicioExcepcion {

        Object result = null;
        try {

            String namedQueryName = (String) queryName.getParam(EParametro.NamedQuery);
            Map parameters = (Map) queryName.getParam(EParametro.ParemtrosQuery);

            Query query = getEntityManager().createNamedQuery(namedQueryName);

            if (parameters != null) {
                Set<Map.Entry> rawParameters = parameters.entrySet();
                for (Entry entry : rawParameters) {
                    //System.out.println(entry.getKey().toString() + ":" + entry.getValue());
                    query.setParameter(entry.getKey().toString(), entry.getValue());
                }
            }

            result = query.getSingleResult();

        } catch (NoResultException nRex) {
        	
        	// throw new ServicioExcepcion("No fue encontrado ningun Registro en la Base de Datos con esa Descripcion ");
        	
           
        } catch (NonUniqueResultException nUniex) {
            throw new ServicioExcepcion("Existen mas de un Registro en la base de Datos con Descripción");
        }


        return result;
    }


    /**
     * se le debe pasar el paraemtro EParametro.EntLocal en el argumento entidad
     */
    
    protected ResponseDTO buscarTodos(RequestDTO clazz) throws ServicioExcepcion {
        //List<Object>
        ResponseDTO response = new ResponseDTO();
        Class<?> clzz = getobject(clazz).getClass();
        String namedQuery = "FROM " + clzz.getSimpleName() + " ";
        Query query = getEntityManager().createQuery(namedQuery);
        List<?> list = verificarPaginacion(query, clazz,null);
        response.setParam(EParametro.ResultList, list);
        return response;

    }

   

    /**
     * Adiciona datos de auditoria al crear un regitro
     *
     * @param request
     */
    protected void addDatosAuditoriaCrear(RequestDTO request) {
        Object entidad = request.getObject();
        if (entidad instanceof EntidadAuditada) {
            EntidadAuditada _entidad = (EntidadAuditada) entidad;
            _entidad.setFecCambio(DateUtils.getFechaSistema());
            _entidad.setFecRegistro(DateUtils.getFechaSistema());
            _entidad.setIdUsuarioCambio(getContextoPrincipal().getIdUsuario());
        }
    }

    /**
     * Adiciona datos de auditoria al modificar un regitro
     *
     * @param request
     */
    protected void addDatosAuditoriaModificar(RequestDTO request) {
        Object entidad = request.getObject();
        if (entidad instanceof EntidadAuditada) {
            EntidadAuditada _entidad = (EntidadAuditada) entidad;
            _entidad.setFecCambio(DateUtils.getFechaSistema());
            _entidad.setIdUsuarioCambio(getContextoPrincipal().getIdUsuario());
        }
    }

    /**
     * Retorna la entidad local que hacer referencia al atributo genérico T
     *
     * @param request
     * @return
     */
    public Object getobject(RequestDTO request) {

        return request.getObject();
    }

    protected List<?> verificarPaginacion(Query query, RequestDTO request,Integer limitResult) {
    	boolean hasLimit=limitResult!=null && limitResult.intValue()>0;

        if (request.isPagination()) {
            query.setFirstResult(request.getPaginationDTO().getIncio());
            query.setMaxResults(request.getPaginationDTO().getRango());


            List<Object> lista =hasLimit? query.setMaxResults(limitResult.intValue()).getResultList():query.getResultList();
            if (lista != null) {
                if (lista.size() < request.getPaginationDTO().getRango())
                    request.getPaginationDTO().setFin(request.getPaginationDTO().getIncio() + lista.size());
                else
                    request.getPaginationDTO().setFin(request.getPaginationDTO().getIncio() + request.getPaginationDTO().getRango());

                request.getPaginationDTO().setIncio(request.getPaginationDTO().getIncio() + lista.size());
            } else {
                request.getPaginationDTO().setFin(request.getPaginationDTO().getIncio());
            }

            return lista;
        } else {
            return hasLimit? query.setMaxResults(limitResult.intValue()).getResultList():query.getResultList();
        }
    }

    /**
     * Realiza la busqueda filtrando los valores no nulos de la entidad pasada
     *
     * @param entityClass
     * @param orderByColumn
     * @param ascending
     * @return
     */
    protected <T> List<T> findAllEntitiesOrderedBy(Class<T> entityClass, String orderByColumn, boolean ascending) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> entityRoot = criteria.from(entityClass);
        criteria.select(entityRoot);
        javax.persistence.criteria.Order order = ascending ? builder.asc(entityRoot.get(orderByColumn))
                : builder.desc(entityRoot.get(orderByColumn));
        criteria.orderBy(order);
        return getEntityManager().createQuery(criteria).getResultList();
    }
    
   

    

    @SuppressWarnings("rawtypes")
    public void maintest(Object entidad) {

        Class clase;
        Field campo, campos[];
        Method metodo, metodos[];

        // Cargamos la clase
        clase = entidad.getClass();//Class.forName(entidad.getClass().getName());

        // Recorremos los campos
        System.out.println("Lista de campos:\n");
        campos = clase.getDeclaredFields();
        for (int i = 0; i < campos.length; i++) {
            campo = campos[i];
            System.out.println("\t" + campo.getName());
        }
        // Recorremos los metodos
        System.out.println("\nLista de metodos:\n");
        metodos = clase.getMethods();
        for (int i = 0; i < metodos.length; i++) {
            metodo = metodos[i];
            System.out.println("\t" + metodo.getName());
        }

    }
}
