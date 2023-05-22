/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import gt.edu.cunoc.controleps.model.entity.EtapasProyecto;
import gt.edu.cunoc.controleps.utils.ProyectoUtils;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author edvin
 */
public class EtapaDto {

    private Integer id;
    private String nombre;
    private String icono;
    private Boolean activo;
    private Boolean editable;
    private List<ComentarioDto> comentarios;

    public EtapaDto() {
    }

    public EtapaDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public EtapaDto(EtapasProyecto etapaProyecto) {
        this.id = etapaProyecto.getIdEtapaFk().getIdEtapa();
        this.nombre = etapaProyecto.getIdEtapaFk().getNombre();
        this.icono= etapaProyecto.getIdEtapaFk().getIcono();
        this.editable = etapaProyecto.getEditable();
        this.activo=false;
        if(etapaProyecto.getEstadoFk().getIdEstadoEps().equals(ProyectoUtils.ID_ESTADO_EPS_ACTIVO)){
            this.activo=true;
            this.comentarios=etapaProyecto.getComentarioList().stream().map(comentario->new ComentarioDto(comentario))
                    .collect(Collectors.toList());
        }   
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ComentarioDto> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDto> comentarios) {
        this.comentarios = comentarios;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
}
