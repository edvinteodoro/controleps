/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import gt.edu.cunoc.controleps.model.entity.Permisos;


/**
 *
 * @author edvin
 */
public class PermisoDto {
    private Integer idPermisos;
    private String titulo;

    public PermisoDto() {
    }

    public PermisoDto(Permisos permiso) {
        this.idPermisos=permiso.getIdPermisos();
        this.titulo=permiso.getTitulo();
    }
    
    public Integer getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(Integer idPermisos) {
        this.idPermisos = idPermisos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
