/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import gt.edu.cunoc.controleps.model.dto.RolDto;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.stream.Collectors;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "rol")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByIdRol", query = "SELECT r FROM Rol r WHERE r.idRol = :idRol"),
    @NamedQuery(name = "Rol.findByTitulo", query = "SELECT r FROM Rol r WHERE r.titulo = :titulo")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_rol")
    private Integer idRol;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "contiene_carrera")
    private Boolean contieneCarrera;
    @Basic(optional = false)
    @Column(name = "contiene_registro")
    private Boolean contieneRegistro;
    @Basic(optional = false)
    @Column(name = "contiene_colegiado")
    private Boolean contieneColegiado;
    @ManyToMany(mappedBy = "rolList")
    private List<Permisos> permisosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRolFk")
    private List<Usuario> usuarioList;

    public Rol() {
    }

    public Rol(RolDto rolDto) {
        this.titulo = rolDto.getTitulo();
        this.permisosList = rolDto.getPermisos().stream().map(permisoDto -> new Permisos()).collect(Collectors.toList());
    }

    public Rol(Integer idRol, String titulo, Boolean contieneCarrera, Boolean contieneRegistro, Boolean contieneColegiado) {
        this.idRol = idRol;
        this.titulo = titulo;
        this.contieneCarrera = contieneCarrera;
        this.contieneRegistro = contieneRegistro;
        this.contieneColegiado = contieneColegiado;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getContieneCarrera() {
        return contieneCarrera;
    }

    public void setContieneCarrera(Boolean contieneCarrera) {
        this.contieneCarrera = contieneCarrera;
    }

    public Boolean getContieneRegistro() {
        return contieneRegistro;
    }

    public void setContieneRegistro(Boolean contieneRegistro) {
        this.contieneRegistro = contieneRegistro;
    }

    public Boolean getContieneColegiado() {
        return contieneColegiado;
    }

    public void setContieneColegiado(Boolean contieneColegiado) {
        this.contieneColegiado = contieneColegiado;
    }

    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.Rol[ idRol=" + idRol + " ]";
    }

}
