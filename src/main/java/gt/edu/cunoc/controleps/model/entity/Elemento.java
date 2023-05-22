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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "elemento")
@NamedQueries({
    @NamedQuery(name = "Elemento.findAll", query = "SELECT e FROM Elemento e"),
    @NamedQuery(name = "Elemento.findByIdElemento", query = "SELECT e FROM Elemento e WHERE e.idElemento = :idElemento"),
    @NamedQuery(name = "Elemento.findByNombre", query = "SELECT e FROM Elemento e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Elemento.findByTipo", query = "SELECT e FROM Elemento e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Elemento.findByTemplate", query = "SELECT e FROM Elemento e WHERE e.template = :template")})
public class Elemento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_elemento")
    private Integer idElemento;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "key")
    private String key;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "template")
    private String template;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElementoFk")
    private List<ElementosProyecto> elementosProyectoList;
    @JoinColumn(name = "id_etapa_fk", referencedColumnName = "id_etapa")
    @ManyToOne(optional = false)
    private Etapa idEtapaFk;

    public Elemento() {
    }

    public Elemento(Integer idElemento) {
        this.idElemento = idElemento;
    }

    public Elemento(Integer idElemento, String nombre, String tipo) {
        this.idElemento = idElemento;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Integer getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Integer idElemento) {
        this.idElemento = idElemento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<ElementosProyecto> getElementosProyectoList() {
        return elementosProyectoList;
    }

    public void setElementosProyectoList(List<ElementosProyecto> elementosProyectoList) {
        this.elementosProyectoList = elementosProyectoList;
    }

    public Etapa getIdEtapaFk() {
        return idEtapaFk;
    }

    public void setIdEtapaFk(Etapa idEtapaFk) {
        this.idEtapaFk = idEtapaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElemento != null ? idElemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elemento)) {
            return false;
        }
        Elemento other = (Elemento) object;
        if ((this.idElemento == null && other.idElemento != null) || (this.idElemento != null && !this.idElemento.equals(other.idElemento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.Elemento[ idElemento=" + idElemento + " ]";
    }
    
}
