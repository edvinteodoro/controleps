/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author edvin
 */
public class ProyectoDto {
    private String titulo;
    private String coordenadas;
    private Integer idCarrera;
    private MultipartFile constanciaInscripcion;
    private MultipartFile constanciaPropedeutico;
    private MultipartFile certificadoNacimiento;
    private MultipartFile cartaAsosorSupervior;
    private MultipartFile anteproyecto;

    public ProyectoDto() {
    }

    public ProyectoDto(String titulo, String coordenadas, Integer idCarrera, MultipartFile constanciaInscripcion,
                       MultipartFile constanciaPropedeutico, MultipartFile certificadoNacimiento, MultipartFile cartaAsosorSupervior,
                       MultipartFile anteproyecto) {
        this.titulo = titulo;
        this.coordenadas = coordenadas;
        this.idCarrera = idCarrera;
        this.constanciaInscripcion = constanciaInscripcion;
        this.constanciaPropedeutico = constanciaPropedeutico;
        this.certificadoNacimiento = certificadoNacimiento;
        this.cartaAsosorSupervior = cartaAsosorSupervior;
        this.anteproyecto = anteproyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public MultipartFile getConstanciaInscripcion() {
        return constanciaInscripcion;
    }

    public void setConstanciaInscripcion(MultipartFile constanciaInscripcion) {
        this.constanciaInscripcion = constanciaInscripcion;
    }

    public MultipartFile getConstanciaPropedeutico() {
        return constanciaPropedeutico;
    }

    public void setConstanciaPropedeutico(MultipartFile constanciaPropedeutico) {
        this.constanciaPropedeutico = constanciaPropedeutico;
    }

    public MultipartFile getCertificadoNacimiento() {
        return certificadoNacimiento;
    }

    public void setCertificadoNacimiento(MultipartFile certificadoNacimiento) {
        this.certificadoNacimiento = certificadoNacimiento;
    }

    public MultipartFile getCartaAsosorSupervior() {
        return cartaAsosorSupervior;
    }

    public void setCartaAsosorSupervior(MultipartFile cartaAsosorSupervior) {
        this.cartaAsosorSupervior = cartaAsosorSupervior;
    }

    public MultipartFile getAnteproyecto() {
        return anteproyecto;
    }

    public void setAnteproyecto(MultipartFile anteproyecto) {
        this.anteproyecto = anteproyecto;
    }
}
