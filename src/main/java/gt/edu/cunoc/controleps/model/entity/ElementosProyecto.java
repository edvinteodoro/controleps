/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import gt.edu.cunoc.controleps.utils.ProyectoUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "elementos_proyecto")
@NamedQueries({
    @NamedQuery(name = "ElementosProyecto.findAll", query = "SELECT e FROM ElementosProyecto e"),
    @NamedQuery(name = "ElementosProyecto.findByIdElementosProyecto", query = "SELECT e FROM ElementosProyecto e WHERE e.idElementosProyecto = :idElementosProyecto"),
    @NamedQuery(name = "ElementosProyecto.findByInformacion", query = "SELECT e FROM ElementosProyecto e WHERE e.informacion = :informacion"),
    @NamedQuery(name = "ElementosProyecto.findByFechaCreacion", query = "SELECT e FROM ElementosProyecto e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ElementosProyecto.findByFechaModificacion", query = "SELECT e FROM ElementosProyecto e WHERE e.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "ElementosProyecto.findByFechaInicio", query = "SELECT e FROM ElementosProyecto e WHERE e.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ElementosProyecto.findByEstado", query = "SELECT e FROM ElementosProyecto e WHERE e.estado = :estado")})
public class ElementosProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_elementos_proyecto")
    private Integer idElementosProyecto;
    @Basic(optional = false)
    @Column(name = "informacion")
    private String informacion;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idElementosProyectoFk")
    private List<Comentario> comentarioList;
    @JoinColumn(name = "id_elemento_fk", referencedColumnName = "id_elemento")
    @ManyToOne(optional = false)
    private Elemento idElementoFk;
    @JoinColumn(name = "id_etapa_proyecto_fk", referencedColumnName = "id_etapa_proyecto")
    @ManyToOne(optional = false)
    private EtapasProyecto idEtapaProyectoFk;

    public ElementosProyecto() {
    }

    public ElementosProyecto(String informacion, Date fechaCreacion, Elemento idElemento,EtapasProyecto etapaProyecto) {
        this.informacion = informacion;
        this.fechaCreacion = fechaCreacion;
        this.estado = ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO;
        this.idElementoFk = idElemento;
        this.idEtapaProyectoFk=etapaProyecto;
    }

    public ElementosProyecto(Date fechaCreacion, String estado, String informacion, Elemento elemento, EtapasProyecto etapaProyecto) {
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.informacion = informacion;
        this.idElementoFk = elemento;
        this.idEtapaProyectoFk = etapaProyecto;
    }

    public ElementosProyecto(Integer idElementosProyecto) {
        this.idElementosProyecto = idElementosProyecto;
    }

    public Integer getIdElementosProyecto() {
        return idElementosProyecto;
    }

    public void setIdElementosProyecto(Integer idElementosProyecto) {
        this.idElementosProyecto = idElementosProyecto;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public Elemento getIdElementoFk() {
        return idElementoFk;
    }

    public void setIdElementoFk(Elemento idElementoFk) {
        this.idElementoFk = idElementoFk;
    }

    public EtapasProyecto getIdEtapaProyectoFk() {
        return idEtapaProyectoFk;
    }

    public void setIdEtapaProyectoFk(EtapasProyecto idEtapaProyectoFk) {
        this.idEtapaProyectoFk = idEtapaProyectoFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElementosProyecto != null ? idElementosProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementosProyecto)) {
            return false;
        }
        ElementosProyecto other = (ElementosProyecto) object;
        if ((this.idElementosProyecto == null && other.idElementosProyecto != null) || (this.idElementosProyecto != null && !this.idElementosProyecto.equals(other.idElementosProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.ElementosProyecto[ idElementosProyecto=" + idElementosProyecto + " ]";
    }

}
