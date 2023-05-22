/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import java.io.Serializable;
import java.util.List;
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

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "etapa")
@NamedQueries({
    @NamedQuery(name = "Etapa.findAll", query = "SELECT e FROM Etapa e"),
    @NamedQuery(name = "Etapa.findByIdEtapa", query = "SELECT e FROM Etapa e WHERE e.idEtapa = :idEtapa"),
    @NamedQuery(name = "Etapa.findByNombre", query = "SELECT e FROM Etapa e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Etapa.findByDescripcion", query = "SELECT e FROM Etapa e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Etapa.findByLimiteDias", query = "SELECT e FROM Etapa e WHERE e.limiteDias = :limiteDias"),
    @NamedQuery(name = "Etapa.findByNotaAprovacion", query = "SELECT e FROM Etapa e WHERE e.notaAprovacion = :notaAprovacion")})
public class Etapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etapa")
    private Integer idEtapa;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "icono")
    private String icono;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limite_dias")
    private Double limiteDias;
    @Column(name = "nota_aprovacion")
    private Double notaAprovacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtapaFk")
    private List<EtapasProyecto> etapasProyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtapaFk")
    private List<Elemento> elementoList;

    public Etapa() {
    }

    public Etapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public Etapa(Integer idEtapa, String nombre, String descripcion) {
        this.idEtapa = idEtapa;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getLimiteDias() {
        return limiteDias;
    }

    public void setLimiteDias(Double limiteDias) {
        this.limiteDias = limiteDias;
    }

    public Double getNotaAprovacion() {
        return notaAprovacion;
    }

    public void setNotaAprovacion(Double notaAprovacion) {
        this.notaAprovacion = notaAprovacion;
    }

    public List<EtapasProyecto> getEtapasProyectoList() {
        return etapasProyectoList;
    }

    public void setEtapasProyectoList(List<EtapasProyecto> etapasProyectoList) {
        this.etapasProyectoList = etapasProyectoList;
    }

    public List<Elemento> getElementoList() {
        return elementoList;
    }

    public void setElementoList(List<Elemento> elementoList) {
        this.elementoList = elementoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtapa != null ? idEtapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etapa)) {
            return false;
        }
        Etapa other = (Etapa) object;
        if ((this.idEtapa == null && other.idEtapa != null) || (this.idEtapa != null && !this.idEtapa.equals(other.idEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.Etapa[ idEtapa=" + idEtapa + " ]";
    }
    
}
