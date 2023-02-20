/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.service;

import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author edvin
 */
public interface UsuarioService {
    public List<Usuario> getAll();
    public Usuario crearUsuario(UsuarioDto usuarioDto);
    public Usuario actualizarUsuario(UsuarioDto usuarioDto);
}
