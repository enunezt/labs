/**
 * 
 */
package com.intermacs.core.model.entidades;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author enunezt
 *
 */

@MappedSuperclass
@XmlRootElement
public abstract class EntidadAuditada {
	

	@Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fecRegistro;
	
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_cambio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fecCambio;
    
    /*@JoinColumn(name = "id_usuario_cambio", referencedColumnName = "id_usuario", nullable = false)
    @ManyToOne(optional = false)*/
    @Column(name = "id_usuario_cambio", nullable = false)
    protected Long idUsuarioCambio;
	
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_cambio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFecCambio() {
        return fecCambio;
    }
    

	public Long getIdUsuarioCambio() {
		return idUsuarioCambio;
	}

	 @Column(name = "id_usuario_cambio", nullable = false)
	public void setIdUsuarioCambio(Long idUsuarioCambio) {
		this.idUsuarioCambio = idUsuarioCambio;
	}

	public void setFecCambio(Date fecCambio) {
		this.fecCambio = fecCambio;
	}
}
