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

    private Integer id;
    private String titulo;

    public CarreraDto() {
    }

    public CarreraDto(Carrera carrera) {
        this.titulo = carrera.getTitulo();
        this.id = carrera.getIdCarrera();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
