/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "token_confirmacion")
@NamedQueries({
    @NamedQuery(name = "TokenConfirmacion.findAll", query = "SELECT t FROM TokenConfirmacion t"),
    @NamedQuery(name = "TokenConfirmacion.findByIdTokenConfirmacion", query = "SELECT t FROM TokenConfirmacion t WHERE t.idTokenConfirmacion = :idTokenConfirmacion"),
    @NamedQuery(name = "TokenConfirmacion.findByToken", query = "SELECT t FROM TokenConfirmacion t WHERE t.token = :token"),
    @NamedQuery(name = "TokenConfirmacion.findByFechaCreacion", query = "SELECT t FROM TokenConfirmacion t WHERE t.fechaCreacion = :fechaCreacion")})
public class TokenConfirmacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_token_confirmacion")
    private Integer idTokenConfirmacion;
    @Basic(optional = false)
    @Column(name = "token")
    private String token;
    @JoinColumn(name = "id_usuario_fk", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioFk;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public TokenConfirmacion() {
    }

    public TokenConfirmacion(Integer idTokenConfirmacion) {
        this.idTokenConfirmacion = idTokenConfirmacion;
    }

    public TokenConfirmacion(String token, Date fechaCreacion) {
        this.token = token;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdTokenConfirmacion() {
        return idTokenConfirmacion;
    }

    public void setIdTokenConfirmacion(Integer idTokenConfirmacion) {
        this.idTokenConfirmacion = idTokenConfirmacion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Usuario idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTokenConfirmacion != null ? idTokenConfirmacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TokenConfirmacion)) {
            return false;
        }
        TokenConfirmacion other = (TokenConfirmacion) object;
        if ((this.idTokenConfirmacion == null && other.idTokenConfirmacion != null) || (this.idTokenConfirmacion != null && !this.idTokenConfirmacion.equals(other.idTokenConfirmacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.TokenConfirmacion[ idTokenConfirmacion=" + idTokenConfirmacion + " ]";
    }
    
}
