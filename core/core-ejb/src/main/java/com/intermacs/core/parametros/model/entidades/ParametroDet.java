/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.parametros.model.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.intermacs.core.model.entidades.EntidadAuditada;
import com.intermacs.core.parametros.model.enums.ETipoDato;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "parametros_det", /*catalog = "db_core",*/ schema = "esq_core")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroDet.findAll", query = "SELECT p FROM ParametroDet p"),
    @NamedQuery(name = "ParametroDet.findByIdParametroDet", query = "SELECT p FROM ParametroDet p WHERE p.idParametroDet = :idParametroDet")})
public class ParametroDet extends EntidadAuditada implements Serializable,Comparable<ParametroDet> {
    private static final long serialVersionUID = 1L;
   
    @Id
	@SequenceGenerator(schema="esq_core",name="parametros_det_id_parametro_det_seqGEN", sequenceName="parametros_det_id_parametro_det_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="parametros_det_id_parametro_det_seqGEN")
	@Column(name="id_parametro_det", unique=true, nullable=false)
    private Long idParametroDet;
    
    @Size(max = 2)
    @Column(name = "tipo_dato", length = 2)
    private ETipoDato tipoDato;
    
    @Column(name = "valor_numerico")
    private BigDecimal valorNumerico;
    
    @Size(max = 100)
    @Column(name = "valor_string", length = 100)
    private String valorString;
    
    @Column(name = "cod_numero")
    private Integer codNumero;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado", nullable = false)
    private Character estado;
    
    @Size(max = 30)
    @Column(name = "cod_string", length = 30)
    private String codString;
   
    @JoinColumn(name = "id_parametro", referencedColumnName = "id_parametro", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametro parametro;

    public ParametroDet() {
    }

    public ParametroDet(Long idParametroDet) {
        this.idParametroDet = idParametroDet;
    }

    public ParametroDet(Long idParametroDet, Character estado, Date fecRegistro) {
        this.idParametroDet = idParametroDet;
        this.estado = estado;
        this.fecRegistro = fecRegistro;
    }

    public Long getIdParametroDet() {
        return idParametroDet;
    }

    public void setIdParametroDet(Long idParametroDet) {
        this.idParametroDet = idParametroDet;
    }

    public ETipoDato getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(ETipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }

    public BigDecimal getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(BigDecimal valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    public Integer getCodNumero() {
        return codNumero;
    }

    public void setCodNumero(Integer codNumero) {
        this.codNumero = codNumero;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodString() {
        return codString;
    }

    public void setCodString(String codString) {
        this.codString = codString;
    }

  

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    @Override
	public int hashCode() {
		
		return new HashCodeBuilder()
        .append(idParametroDet)
        .toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		 if(obj instanceof ParametroDet){
 	        final ParametroDet other = (ParametroDet) obj;
 	        return new EqualsBuilder()
 	            .append(idParametroDet, other.idParametroDet)
 	            .isEquals();
 	    } else{
 	        return false;
 	    }
	}

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.ParametroDet[ idParametroDet=" + idParametroDet + " ]";
    }
    
    public int compareTo(ParametroDet o) {
		 ParametroDet myClass = (ParametroDet) o;
	     return new CompareToBuilder()
	       .append(this.idParametroDet, myClass.idParametroDet)
	       .toComparison();
	   }
    
}
