/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "constantes")
@NamedQueries({
    @NamedQuery(name = "Constantes.findAll", query = "SELECT c FROM Constantes c"),
    @NamedQuery(name = "Constantes.findByIdConstante", query = "SELECT c FROM Constantes c WHERE c.idConstante = :idConstante"),
    @NamedQuery(name = "Constantes.findByValor", query = "SELECT c FROM Constantes c WHERE c.valor = :valor"),
    @NamedQuery(name = "Constantes.findByNombre", query = "SELECT c FROM Constantes c WHERE c.nombre = :nombre")})
public class Constantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_constante")
    private Integer idConstante;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @Column(name = "nombre")
    private String nombre;

    public Constantes() {
    }

    public Constantes(Integer idConstante) {
        this.idConstante = idConstante;
    }

    public Constantes(Integer idConstante, String valor) {
        this.idConstante = idConstante;
        this.valor = valor;
    }

    public Integer getIdConstante() {
        return idConstante;
    }

    public void setIdConstante(Integer idConstante) {
        this.idConstante = idConstante;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConstante != null ? idConstante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Constantes)) {
            return false;
        }
        Constantes other = (Constantes) object;
        if ((this.idConstante == null && other.idConstante != null) || (this.idConstante != null && !this.idConstante.equals(other.idConstante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.Constantes[ idConstante=" + idConstante + " ]";
    }
    
}
