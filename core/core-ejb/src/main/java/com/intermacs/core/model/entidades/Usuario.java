/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intermacs.core.model.entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.intermacs.core.enums.ETipoUsuario;
import com.intermacs.images.model.entidades.Imagen;

/**
 *
 * @author eanunezt
 */
@Entity
@Table(name = "usuarios", /*catalog = "db_core",*/ schema = "esq_core", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_usuario"}),
    @UniqueConstraint(columnNames = {"num_documento"})})
@XmlRootElement
@NamedQueries({
	@NamedQuery(name=Usuario.NQ_findAll, query="SELECT u FROM Usuario u"),
		@NamedQuery(name=Usuario.NQ_findByPassAndUser, query="SELECT u FROM Usuario u where u.usuario=:usuario and u.clave=:passEncrypt"),
		@NamedQuery(name=Usuario.NQ_findByNumDocumento, query="SELECT u FROM Usuario u where u.numDocumento=:numDocumento"),
		@NamedQuery( name = Usuario.NQ_findTipoDocNumDoc, query = "SELECT p FROM Usuario p WHERE p.tipoDocumento.idCatalogoDet = :idCatalogoDet and p.numDocumento  = :numDocumento ")
	})
public class Usuario extends EntidadAuditada implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final String  SEQ_NAME="usuario_id_usuario_seq";
	private static final String  SEQ_ALIAS="usuarioIdUsuarioSeq";
	public static final String  NQ_findAll="Usuario.findAll";
	public static final String  NQ_findByPassAndUser="Usuario.findByPassAndUser";
	public static final String  NQ_findByNumDocumento="Usuario.findByNumDocumento";
	public static final String  NQ_findTipoDocNumDoc="Usuario.findTipoDocNumDoc";
	
	public static final String PAR_USARIO="usuario";
	public static final String PAR_PASSWORD="passEncrypt";
	public static final String PAR_IDE_CATALOGO_DET="idCatalogoDet";
	public static final String PAR_NUM_DOCUMENTO="numDocumento";
    
    
    @Id
    @Basic(optional = false)
    @Column(name = "id_usuario", nullable = false)
	@SequenceGenerator(schema="esq_core", name = Usuario.SEQ_ALIAS, sequenceName =Usuario.SEQ_NAME, allocationSize = 1)
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = Usuario.SEQ_ALIAS )
    private Long idUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido1", nullable = false, length = 30)
    private String apellido1;
    
    @Size(max = 30)
    @Column(name = "apellido2", length = 30)
    private String apellido2;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "clave", nullable = false, length = 255)
    private String clave;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "email", length = 60)
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "num_documento", nullable = false, length = 30)
    private String numDocumento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_usuario", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ETipoUsuario tipoUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "usuario", nullable = false, length = 10)
    private String usuario;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
    private Collection<RolUsuario> rolUsuarioCollection;
   
  
    
    @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen")
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.DETACH)
    private Imagen fotoPerfil;
    
    @JoinColumn(name = "tipo_documento", referencedColumnName = "id_catalogo_det", nullable = false)
    @ManyToOne(optional = false, cascade=CascadeType.DETACH)
    private CatalogoDet tipoDocumento;
    
    @Transient
    private String claveLegible;

   

	public Usuario() {
    }

    public Usuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Long idUsuario, String apellido1, String clave, String nombres, String numDocumento, ETipoUsuario tipoUsuario, String usuario) {
        this.idUsuario = idUsuario;
        this.apellido1 = apellido1;
        this.clave = clave;
        this.nombres = nombres;
        this.numDocumento = numDocumento;
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

  


    @XmlTransient
    public Collection<RolUsuario> getRolUsuarioCollection() {
        return rolUsuarioCollection;
    }

    public void setRolUsuarioCollection(Collection<RolUsuario> rolUsuarioCollection) {
        this.rolUsuarioCollection = rolUsuarioCollection;
    }

    public Imagen getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Imagen fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public CatalogoDet getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(CatalogoDet tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public String getClaveLegible() {
		return claveLegible;
	}

	public void setClaveLegible(String claveLegible) {
		this.claveLegible = claveLegible;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.intermacs.core.model.entidades.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
