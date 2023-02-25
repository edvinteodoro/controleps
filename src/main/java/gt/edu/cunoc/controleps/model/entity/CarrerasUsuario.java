/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "carreras_usuario")
@NamedQueries({
    @NamedQuery(name = "CarrerasUsuario.findAll", query = "SELECT c FROM CarrerasUsuario c"),
    @NamedQuery(name = "CarrerasUsuario.findByIdUsuarioCarrera", query = "SELECT c FROM CarrerasUsuario c WHERE c.idUsuarioCarrera = :idUsuarioCarrera"),
    @NamedQuery(name = "CarrerasUsuario.findByIntentos", query = "SELECT c FROM CarrerasUsuario c WHERE c.intentos = :intentos")})
public class CarrerasUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_carrera")
    private Integer idUsuarioCarrera;
    @Basic(optional = true)
    @Column(name = "INTENTOS")
    private int intentos;
    @JoinColumn(name = "id_carrera_fk", referencedColumnName = "id_carrera")
    @ManyToOne(optional = false)
    private Carrera idCarreraFk;
    @JoinColumn(name = "id_usuario_fk", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioFk;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrerasSupervisorFk")
    private List<ProyectoEps> proyectoEpsSupervisorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrerasAsesorFk")
    private List<ProyectoEps> proyectoEpsAsesorList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrerasUsuarioFk")
    private List<ProyectoEps> proyectoEpsList;

    public CarrerasUsuario(){
    }
    
    public CarrerasUsuario(Carrera carrera, Usuario usuario) {
        this.idCarreraFk = carrera;
        this.idUsuarioFk = usuario;
    }

    public CarrerasUsuario(Integer idUsuarioCarrera) {
        this.idUsuarioCarrera = idUsuarioCarrera;
    }

    public CarrerasUsuario(Integer idUsuarioCarrera, int intentos) {
        this.idUsuarioCarrera = idUsuarioCarrera;
        this.intentos = intentos;
    }

    public Integer getIdUsuarioCarrera() {
        return idUsuarioCarrera;
    }

    public void setIdUsuarioCarrera(Integer idUsuarioCarrera) {
        this.idUsuarioCarrera = idUsuarioCarrera;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Carrera getIdCarreraFk() {
        return idCarreraFk;
    }

    public void setIdCarreraFk(Carrera idCarreraFk) {
        this.idCarreraFk = idCarreraFk;
    }

    public Usuario getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Usuario idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public List<ProyectoEps> getProyectoEpsSupervisorList() {
        return proyectoEpsSupervisorList;
    }

    public void setProyectoEpsSupervisorList(List<ProyectoEps> proyectoEpsSupervisorList) {
        this.proyectoEpsSupervisorList = proyectoEpsSupervisorList;
    }

    public List<ProyectoEps> getProyectoEpsAsesorList() {
        return proyectoEpsAsesorList;
    }

    public void setProyectoEpsAsesorList(List<ProyectoEps> proyectoEpsAsesorList) {
        this.proyectoEpsAsesorList = proyectoEpsAsesorList;
    }

    public List<ProyectoEps> getProyectoEpsList() {
        return proyectoEpsList;
    }

    public void setProyectoEpsList(List<ProyectoEps> proyectoEpsList) {
        this.proyectoEpsList = proyectoEpsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioCarrera != null ? idUsuarioCarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarrerasUsuario)) {
            return false;
        }
        CarrerasUsuario other = (CarrerasUsuario) object;
        if ((this.idUsuarioCarrera == null && other.idUsuarioCarrera != null) || (this.idUsuarioCarrera != null && !this.idUsuarioCarrera.equals(other.idUsuarioCarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.CarrerasUsuario[ idUsuarioCarrera=" + idUsuarioCarrera + " ]";
    }
    
}
