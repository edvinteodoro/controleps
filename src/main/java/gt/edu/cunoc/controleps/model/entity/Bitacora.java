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
@Table(name = "bitacora")
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByIdBitacora", query = "SELECT b FROM Bitacora b WHERE b.idBitacora = :idBitacora"),
    @NamedQuery(name = "Bitacora.findByFuncionalidad", query = "SELECT b FROM Bitacora b WHERE b.funcionalidad = :funcionalidad"),
    @NamedQuery(name = "Bitacora.findByDescripcion", query = "SELECT b FROM Bitacora b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "Bitacora.findByFecha", query = "SELECT b FROM Bitacora b WHERE b.fecha = :fecha"),
    @NamedQuery(name = "Bitacora.findByRequest", query = "SELECT b FROM Bitacora b WHERE b.request = :request"),
    @NamedQuery(name = "Bitacora.findByResponse", query = "SELECT b FROM Bitacora b WHERE b.response = :response"),
    @NamedQuery(name = "Bitacora.findByEstatusHttps", query = "SELECT b FROM Bitacora b WHERE b.estatusHttps = :estatusHttps")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora")
    private Integer idBitacora;
    @Basic(optional = false)
    @Column(name = "funcionalidad")
    private String funcionalidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "request")
    private String request;
    @Basic(optional = false)
    @Column(name = "response")
    private String response;
    @Basic(optional = false)
    @Column(name = "estatus_https")
    private int estatusHttps;
    @JoinColumn(name = "id_usuario_fk", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioFk;
    @JoinColumn(name = "id_usuario_afectad_fk", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioAfectadFk;

    public Bitacora() {
    }

    public Bitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Bitacora(Integer idBitacora, String funcionalidad, String descripcion, Date fecha, String request, String response, int estatusHttps) {
        this.idBitacora = idBitacora;
        this.funcionalidad = funcionalidad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.request = request;
        this.response = response;
        this.estatusHttps = estatusHttps;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(String funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getEstatusHttps() {
        return estatusHttps;
    }

    public void setEstatusHttps(int estatusHttps) {
        this.estatusHttps = estatusHttps;
    }

    public Usuario getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Usuario idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public Usuario getIdUsuarioAfectadFk() {
        return idUsuarioAfectadFk;
    }

    public void setIdUsuarioAfectadFk(Usuario idUsuarioAfectadFk) {
        this.idUsuarioAfectadFk = idUsuarioAfectadFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.Bitacora[ idBitacora=" + idBitacora + " ]";
    }
    
}
