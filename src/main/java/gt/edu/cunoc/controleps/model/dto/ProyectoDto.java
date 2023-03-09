/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author edvin
 */
public class ProyectoDto {
    private Integer idProyecto;
    private String titulo;
    private String coordenadas;
    private CarreraDto carrera;
    private MultipartFile constanciaInscripcion;
    private MultipartFile constanciaPropedeutico;
    private MultipartFile certificadoNacimiento;
    private MultipartFile cartaAsesorSupervisor;
    private MultipartFile anteproyecto;

    public ProyectoDto() {
    }

    public ProyectoDto(String titulo, String coordenadas, CarreraDto carrera, MultipartFile constanciaInscripcion,
                       MultipartFile constanciaPropedeutico, MultipartFile certificadoNacimiento, MultipartFile cartaAsesorSupervisor,
                       MultipartFile anteproyecto) {
        
        this.titulo = titulo;
        this.coordenadas = coordenadas;
        this.carrera = carrera;
        this.constanciaInscripcion = constanciaInscripcion;
        this.constanciaPropedeutico = constanciaPropedeutico;
        this.certificadoNacimiento = certificadoNacimiento;
        this.cartaAsesorSupervisor = cartaAsesorSupervisor;
        this.anteproyecto = anteproyecto;
    }
    
    public ProyectoDto(ProyectoEps proyecto) {
        this.idProyecto = proyecto.getIdAnteproyecto();
        this.titulo=proyecto.getTitulo();
        this.coordenadas=proyecto.getCoordenadas();
        this.carrera=new CarreraDto(proyecto.getIdCarrerasUsuarioFk().getIdCarreraFk()); 
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
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

    public CarreraDto getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraDto carrera) {
        this.carrera = carrera;
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

    public MultipartFile getCartaAsesorSupervisor() {
        return cartaAsesorSupervisor;
    }

    public void setCartaAsesorSupervisor(MultipartFile cartaAsesorSupervisor) {
        this.cartaAsesorSupervisor = cartaAsesorSupervisor;
    }

    public MultipartFile getAnteproyecto() {
        return anteproyecto;
    }

    public void setAnteproyecto(MultipartFile anteproyecto) {
        this.anteproyecto = anteproyecto;
    }
}
