/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author edvin
 */
public class UsuarioDto {

    private Integer idUsuario;
    private String correo;
    private Date fechaNacimiento;
    private String nombres;
    private String apellidos;
    private String registroAcademico;
    private String numeroColegiado;
    private String telefono;
    private String dpi;
    private String direccion;
    private String estadoCuenta;
    private List<CarreraDto> carreras;
    //private String mobile;
    //private Date registeredAt;
    //private Date lastLogin;
    @JsonInclude(Include.NON_NULL)
    private RolDto rol;

    public UsuarioDto() {
    }

    public UsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.correo = usuario.getCorreo();
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.registroAcademico = usuario.getRegistroAcademico();
        this.numeroColegiado = usuario.getNumeroColegiado();
        this.estadoCuenta = usuario.getEstadoCuenta();
        this.telefono = usuario.getTelefono();
        this.dpi = usuario.getDpi();
        this.direccion = usuario.getDirreccion();
        this.rol = new RolDto(usuario.getIdRolFk());
        this.carreras = usuario.getCarrerasUsuarioList().stream()
                .map(usuarioCarrera -> new CarreraDto(usuarioCarrera.getIdCarreraFk())).collect(Collectors.toList());
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRegistroAcademico() {
        return registroAcademico;
    }

    public void setRegistroAcademico(String registroAcademico) {
        this.registroAcademico = registroAcademico;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estado) {
        this.estadoCuenta = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto rol) {
        this.rol = rol;
    }

    public List<CarreraDto> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<CarreraDto> carreras) {
        this.carreras = carreras;
    }
}
