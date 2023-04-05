/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gt.edu.cunoc.controleps.model.entity.ElementosProyecto;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import java.util.ArrayList;
import java.util.List;
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
    private List<Requisito> requisitos;
    private UsuarioDto usuario;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile constanciaInscripcion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile constanciaPropedeutico;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile certificadoNacimiento;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile cartaAsesorSupervisor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
        this.titulo = proyecto.getTitulo();
        this.coordenadas = proyecto.getCoordenadas();
        this.carrera = new CarreraDto(proyecto.getIdCarrerasUsuarioFk().getIdCarreraFk());
        this.usuario = new UsuarioDto(proyecto.getIdCarrerasUsuarioFk().getIdUsuarioFk());
        if (proyecto.getEtapasProyectoList() != null && !proyecto.getEtapasProyectoList().isEmpty()) {
            this.requisitos = new ArrayList<>();
            for (ElementosProyecto elemento : proyecto.getEtapasProyectoList().get(0).getElementosProyectoList()) {
                Requisito requisito = new Requisito(elemento.getIdElementosProyecto(), elemento.getInformacion());
                this.requisitos.add(requisito);
            }
        }
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

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
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

    public List<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    public static class Requisito {

        private int id;
        private String link;

        public Requisito(int id, String link) {
            this.id = id;
            this.link = link;
        }

        // constructor, getters, and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
