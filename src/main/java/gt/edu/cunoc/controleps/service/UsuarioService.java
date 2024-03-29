/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.service;

import gt.edu.cunoc.controleps.model.dto.ActivarUsuarioDto;
import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author edvin
 */
public interface UsuarioService {
    public List<Usuario> getAll();
    public Usuario crearUsuario(UsuarioDto usuarioDto) throws Exception;
    public Usuario guardarUsuario(UsuarioDto usuarioDto) throws Exception;
    public Usuario crearAsesor(UsuarioDto usuarioDto) throws Exception;
    public Usuario actualizarUsuario(UsuarioDto usuarioDto);
    public Optional<Usuario> getSupervisorDisponible(Integer idCarrera);
    public Optional<Usuario> getUsuarioPorRegistor(String registroAcademico);
    public Optional<Usuario> getUsuarioPorColegiado(String numeroColegiado);
    public Optional<Usuario> getUsuarioPorNombreUsuario(String nombreUsuario);
    public Optional<Usuario> getSecretariaDisponible();
    public Usuario activarUsuario(ActivarUsuarioDto activarUsuarioDto) throws Exception;
}
