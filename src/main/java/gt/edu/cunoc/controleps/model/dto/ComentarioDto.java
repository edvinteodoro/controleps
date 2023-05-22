/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gt.edu.cunoc.controleps.model.entity.Comentario;
import java.util.Date;

/**
 *
 * @author edvin
 */
public class ComentarioDto {

    private Integer id;
    private UsuarioDto usuario;
    private String texto;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fecha;

    public ComentarioDto() {
    }

    public ComentarioDto(Comentario comentario) {
        this.id = comentario.getIdComentario();
        this.usuario = new UsuarioDto(comentario.getIdUsuarioFk());
        this.texto = comentario.getTexto();
        this.fecha = comentario.getFechaCreacion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
