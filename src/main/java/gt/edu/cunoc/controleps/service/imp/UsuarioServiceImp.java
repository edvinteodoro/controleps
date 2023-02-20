/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import gt.edu.cunoc.controleps.model.entity.Rol;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.model.repository.UsuarioRepository;
import gt.edu.cunoc.controleps.service.UsuarioService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author edvin
 */
@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolServiceImp rolService;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crearUsuario(UsuarioDto usuarioDto) {
        //get role
        //set role to user
        //set user creation date
        Usuario usuarioNuevo = new Usuario(usuarioDto);
        usuarioNuevo.setPassword(new BCryptPasswordEncoder().encode("test123"));
        Rol rol = rolService.getRolByIdRol(usuarioDto.getRol().getIdRol());
        usuarioNuevo.setIdRolFk(rol);
        LocalDate currentDate = LocalDate.now();
        //usuarioNuevo.setRegisteredAt(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return usuarioRepository.save(usuarioNuevo);
        /*newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles(user.getRoles());*/
    }

    @Override
    public Usuario actualizarUsuario(UsuarioDto usuarioDto) {
        return null;
    }

}
