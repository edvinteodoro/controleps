/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.repository.UsuarioRepository;
import gt.edu.cunoc.controleps.utils.UsuarioUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author edvin
 */
@Service
public class UsuarioDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = usuarioRepository.findByRegistroAcademico(username);
        if (!optionalUser.isPresent()) {
            optionalUser = usuarioRepository.findByNumeroColegiado(username);
            if (!optionalUser.isPresent()) {
                throw new UsernameNotFoundException("No se encontro el usuario: " + username);
            }
        }
        Usuario usuario = optionalUser.get();
        if(!usuario.getEstadoCuenta().equals(UsuarioUtils.USUARIO_ACTIVO)){
            throw new UsernameNotFoundException("Este usuario esta inactivo: " + username);
        }
        return org.springframework.security.core.userdetails.User.withUsername(username).password(usuario.getPassword()).roles("ADMIN")
                .authorities(usuario.getIdRolFk().getTitulo()).build();
    }
}
