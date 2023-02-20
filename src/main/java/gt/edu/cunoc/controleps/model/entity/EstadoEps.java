/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "estado_eps")
@NamedQueries({
    @NamedQuery(name = "EstadoEps.findAll", query = "SELECT e FROM EstadoEps e"),
    @NamedQuery(name = "EstadoEps.findByIdEstadoEps", query = "SELECT e FROM EstadoEps e WHERE e.idEstadoEps = :idEstadoEps"),
    @NamedQuery(name = "EstadoEps.findByNombre", query = "SELECT e FROM EstadoEps e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoEps.findByDescripcion", query = "SELECT e FROM EstadoEps e WHERE e.descripcion = :descripcion")})
public class EstadoEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_eps")
    private Integer idEstadoEps;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoFk")
    private List<EtapasProyecto> etapasProyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoEpsFk")
    private List<ProyectoEps> proyectoEpsList;

    public EstadoEps() {
    }

    public EstadoEps(Integer idEstadoEps) {
        this.idEstadoEps = idEstadoEps;
    }

    public EstadoEps(Integer idEstadoEps, String nombre, String descripcion) {
        this.idEstadoEps = idEstadoEps;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdEstadoEps() {
        return idEstadoEps;
    }

    public void setIdEstadoEps(Integer idEstadoEps) {
        this.idEstadoEps = idEstadoEps;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EtapasProyecto> getEtapasProyectoList() {
        return etapasProyectoList;
    }

    public void setEtapasProyectoList(List<EtapasProyecto> etapasProyectoList) {
        this.etapasProyectoList = etapasProyectoList;
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
        hash += (idEstadoEps != null ? idEstadoEps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoEps)) {
            return false;
        }
        EstadoEps other = (EstadoEps) object;
        if ((this.idEstadoEps == null && other.idEstadoEps != null) || (this.idEstadoEps != null && !this.idEstadoEps.equals(other.idEstadoEps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.EstadoEps[ idEstadoEps=" + idEstadoEps + " ]";
    }
    
}
