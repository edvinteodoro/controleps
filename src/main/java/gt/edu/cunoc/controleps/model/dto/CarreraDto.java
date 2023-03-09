/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import gt.edu.cunoc.controleps.model.entity.Carrera;

/**
 *
 * @author edvin
 */
public class CarreraDto {

    private Integer idCarrera;
    private String titulo;

    public CarreraDto() {
    }

    public CarreraDto(Carrera carrera) {
        this.titulo = carrera.getTitulo();
        this.idCarrera = carrera.getIdCarrera();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

}
