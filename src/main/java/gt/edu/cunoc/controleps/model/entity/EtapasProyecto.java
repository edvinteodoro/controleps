/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
@Table(name = "etapas_proyecto")
@NamedQueries({
    @NamedQuery(name = "EtapasProyecto.findAll", query = "SELECT e FROM EtapasProyecto e"),
    @NamedQuery(name = "EtapasProyecto.findByIdEtapaProyecto", query = "SELECT e FROM EtapasProyecto e WHERE e.idEtapaProyecto = :idEtapaProyecto"),
    @NamedQuery(name = "EtapasProyecto.findByFechaCreacion", query = "SELECT e FROM EtapasProyecto e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "EtapasProyecto.findByFechaModificacion", query = "SELECT e FROM EtapasProyecto e WHERE e.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "EtapasProyecto.findByFechaFin", query = "SELECT e FROM EtapasProyecto e WHERE e.fechaFin = :fechaFin")})
public class EtapasProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etapa_proyecto")
    private Integer idEtapaProyecto;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(mappedBy = "etapaProyectoFk")
    private List<Comentario> comentarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtapaProyectoFk")
    private List<ElementosProyecto> elementosProyectoList;
    @JoinColumn(name = "estado_fk", referencedColumnName = "id_estado_eps")
    @ManyToOne(optional = false)
    private EstadoEps estadoFk;
    @JoinColumn(name = "id_etapa_fk", referencedColumnName = "id_etapa")
    @ManyToOne(optional = false)
    private Etapa idEtapaFk;
    @JoinColumn(name = "id_proyecto_eps_fk", referencedColumnName = "id_anteproyecto")
    @ManyToOne(optional = false)
    private ProyectoEps idProyectoEpsFk;

    public EtapasProyecto() {
    }

    public EtapasProyecto(Integer idEtapaProyecto) {
        this.idEtapaProyecto = idEtapaProyecto;
    }

    public EtapasProyecto(Integer idEtapaProyecto, Date fechaCreacion, Date fechaModificacion) {
        this.idEtapaProyecto = idEtapaProyecto;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdEtapaProyecto() {
        return idEtapaProyecto;
    }

    public void setIdEtapaProyecto(Integer idEtapaProyecto) {
        this.idEtapaProyecto = idEtapaProyecto;
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

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public List<ElementosProyecto> getElementosProyectoList() {
        return elementosProyectoList;
    }

    public void setElementosProyectoList(List<ElementosProyecto> elementosProyectoList) {
        this.elementosProyectoList = elementosProyectoList;
    }

    public EstadoEps getEstadoFk() {
        return estadoFk;
    }

    public void setEstadoFk(EstadoEps estadoFk) {
        this.estadoFk = estadoFk;
    }

    public Etapa getIdEtapaFk() {
        return idEtapaFk;
    }

    public void setIdEtapaFk(Etapa idEtapaFk) {
        this.idEtapaFk = idEtapaFk;
    }

    public ProyectoEps getIdProyectoEpsFk() {
        return idProyectoEpsFk;
    }

    public void setIdProyectoEpsFk(ProyectoEps idProyectoEpsFk) {
        this.idProyectoEpsFk = idProyectoEpsFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtapaProyecto != null ? idEtapaProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtapasProyecto)) {
            return false;
        }
        EtapasProyecto other = (EtapasProyecto) object;
        if ((this.idEtapaProyecto == null && other.idEtapaProyecto != null) || (this.idEtapaProyecto != null && !this.idEtapaProyecto.equals(other.idEtapaProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.EtapasProyecto[ idEtapaProyecto=" + idEtapaProyecto + " ]";
    }
    
}
