/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
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
@Table(name = "proyecto_eps")
@NamedQueries({
    @NamedQuery(name = "ProyectoEps.findAll", query = "SELECT p FROM ProyectoEps p"),
    @NamedQuery(name = "ProyectoEps.findByIdAnteproyecto", query = "SELECT p FROM ProyectoEps p WHERE p.idAnteproyecto = :idAnteproyecto"),
    @NamedQuery(name = "ProyectoEps.findByFechaInicio", query = "SELECT p FROM ProyectoEps p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ProyectoEps.findByFechaFin", query = "SELECT p FROM ProyectoEps p WHERE p.fechaFin = :fechaFin")})
public class ProyectoEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_anteproyecto")
    private Integer idAnteproyecto;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "coordenadas")
    private String coordenadas;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional=true)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyectoEpsFk")
    private List<EtapasProyecto> etapasProyectoList;
    @JoinColumn(name = "id_estado_eps_fk", referencedColumnName = "id_estado_eps")
    @ManyToOne(optional = false)
    private EstadoEps idEstadoEpsFk;
    @JoinColumn(name = "id_carreras_usuario_fk", referencedColumnName = "id_usuario_carrera")
    @ManyToOne(optional = false)
    private CarrerasUsuario idCarrerasUsuarioFk;
    @JoinColumn(name = "id_carreras_supervisor_fk", referencedColumnName = "id_usuario_carrera")
    @ManyToOne(optional = false)
    private CarrerasUsuario idCarrerasSupervisorFk;
    @JoinColumn(name = "id_carreras_asesor_fk", referencedColumnName = "id_usuario_carrera")
    @ManyToOne(optional=true)
    private CarrerasUsuario idCarrerasAsesorFk;
    @JoinColumn(name = "id_secretaria_fk", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idSecretariaFk;

    public ProyectoEps() {
    }
    
    public ProyectoEps(ProyectoDto proyectoDto) {
        this.titulo=proyectoDto.getTitulo();
        this.coordenadas=proyectoDto.getCoordenadas();
    }

    public ProyectoEps(Integer idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
    }

    public ProyectoEps(Integer idAnteproyecto, String titulo, String coordenandas, Date fechaInicio, Date fechaFin) {
        this.idAnteproyecto = idAnteproyecto;
        this.titulo = titulo;
        this.coordenadas = coordenandas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdAnteproyecto() {
        return idAnteproyecto;
    }

    public void setIdAnteproyecto(Integer idAnteproyecto) {
        this.idAnteproyecto = idAnteproyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<EtapasProyecto> getEtapasProyectoList() {
        return etapasProyectoList;
    }

    public void setEtapasProyectoList(List<EtapasProyecto> etapasProyectoList) {
        this.etapasProyectoList = etapasProyectoList;
    }

    public EstadoEps getIdEstadoEpsFk() {
        return idEstadoEpsFk;
    }

    public void setIdEstadoEpsFk(EstadoEps idEstadoEpsFk) {
        this.idEstadoEpsFk = idEstadoEpsFk;
    }

    public CarrerasUsuario getIdCarrerasUsuarioFk() {
        return idCarrerasUsuarioFk;
    }

    public void setIdCarrerasUsuarioFk(CarrerasUsuario idCarrerasUsuarioFk) {
        this.idCarrerasUsuarioFk = idCarrerasUsuarioFk;
    }

    public CarrerasUsuario getIdCarrerasSupervisorFk() {
        return idCarrerasSupervisorFk;
    }

    public void setIdCarrerasSupervisorFk(CarrerasUsuario idCarrerasSupervisorFk) {
        this.idCarrerasSupervisorFk = idCarrerasSupervisorFk;
    }

    public CarrerasUsuario getIdCarrerasAsesorFk() {
        return idCarrerasAsesorFk;
    }

    public void setIdCarrerasAsesorFk(CarrerasUsuario idCarrerasAsesorFk) {
        this.idCarrerasAsesorFk = idCarrerasAsesorFk;
    }

    public Usuario getIdSecretariaFk() {
        return idSecretariaFk;
    }

    public void setIdSecretariaFk(Usuario idSecretariaFk) {
        this.idSecretariaFk = idSecretariaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnteproyecto != null ? idAnteproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoEps)) {
            return false;
        }
        ProyectoEps other = (ProyectoEps) object;
        if ((this.idAnteproyecto == null && other.idAnteproyecto != null) || (this.idAnteproyecto != null && !this.idAnteproyecto.equals(other.idAnteproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.ProyectoEps[ idAnteproyecto=" + idAnteproyecto + " ]";
    }

}
