/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import gt.edu.cunoc.controleps.model.entity.Carrera;
import gt.edu.cunoc.controleps.model.entity.CarrerasUsuario;
import gt.edu.cunoc.controleps.model.entity.Rol;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.repository.CarreraRepository;
import gt.edu.cunoc.controleps.repository.UsuarioRepository;
import gt.edu.cunoc.controleps.service.UsuarioService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author edvin
 */
@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolServiceImp rolService;
    private final CarreraRepository carreraRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, RolServiceImp rolService, CarreraRepository carreraRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
        this.carreraRepository = carreraRepository;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crearUsuario(UsuarioDto usuarioDto) {
        Usuario usuarioNuevo = new Usuario(usuarioDto);
        usuarioNuevo.setPassword(new BCryptPasswordEncoder().encode("test123"));
        Rol rol = rolService.getRolByIdRol(usuarioDto.getRol().getIdRol());
        List<CarrerasUsuario> carreras = usuarioDto.getCarreras().stream().map(carreraDto -> {
            Carrera carrera = carreraRepository.findById(carreraDto.getId())
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado la carrera seleccionada"));
            return new CarrerasUsuario(carrera,usuarioNuevo);
        }).collect(Collectors.toList()); 
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

    @Override
    public Usuario getUsuarioDisponible(Integer idCarrera, Integer idRol) {
        return usuarioRepository.getUsuarioDisponible(idCarrera, idRol).orElseThrow(() -> new RuntimeException("No se ha encontrado ningun supervisor para esta carrera"));
    }

    @Override
    public Optional<Usuario> getUsuarioPorRegistor(String registroAcademico) {
        return usuarioRepository.findByRegistroAcademico(registroAcademico);
    }

}
