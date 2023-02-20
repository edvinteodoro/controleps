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
    private List<PermisoDto> permisos;

    public RolDto() {
    }
    
    public RolDto(Rol rol) {
        this.idRol=rol.getIdRol();
        this.titulo=rol.getTitulo();
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

    public List<PermisoDto> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoDto> permisos) {
        this.permisos = permisos;
    }

    
}
