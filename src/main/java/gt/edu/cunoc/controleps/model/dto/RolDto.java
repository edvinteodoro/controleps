/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import gt.edu.cunoc.controleps.model.entity.Rol;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author edvin
 */
public class RolDto {
    private Integer idRol;
    private String titulo;
    private Boolean contieneCarrera;
    private Boolean contieneRegistro;
    private Boolean contieneColegiado;
    private List<PermisoDto> permisos;

    public RolDto() {
    }
    
    public RolDto(Rol rol) {
        this.idRol=rol.getIdRol();
        this.titulo=rol.getTitulo();
        this.contieneCarrera=rol.getContieneCarrera();
        this.contieneRegistro=rol.getContieneRegistro();
        this.contieneColegiado=rol.getContieneColegiado();
        this.permisos=rol.getPermisosList().stream().map(permiso-> new PermisoDto(permiso)).collect(Collectors.toList());
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
    
    public List<PermisoDto> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoDto> permisos) {
        this.permisos = permisos;
    }

    
}
