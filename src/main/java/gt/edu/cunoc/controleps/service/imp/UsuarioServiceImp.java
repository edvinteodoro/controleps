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
import gt.edu.cunoc.controleps.repository.CarreraUsuarioRepository;
import gt.edu.cunoc.controleps.repository.UsuarioRepository;
import gt.edu.cunoc.controleps.service.UsuarioService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    private final CarreraUsuarioRepository carreraUsuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, RolServiceImp rolService, CarreraRepository carreraRepository, CarreraUsuarioRepository carreraUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
        this.carreraRepository = carreraRepository;
        this.carreraUsuarioRepository = carreraUsuarioRepository;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crearUsuario(UsuarioDto usuarioDto) {
        usuarioDto.setEstadoCuenta("ACTIVO");
        Usuario usuarioNuevo = new Usuario(usuarioDto);
        usuarioNuevo.setPassword(new BCryptPasswordEncoder().encode("test123"));
        Rol rol = rolService.getRolByIdRol(usuarioDto.getRol().getIdRol())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario"));
        usuarioNuevo.setIdRolFk(rol);
        if (usuarioDto.getCarreras() != null) {
            List<CarrerasUsuario> carreras = usuarioDto.getCarreras().stream().map(carreraDto -> {
                Carrera carrera = carreraRepository.findById(carreraDto.getIdCarrera())
                        .orElseThrow(() -> new RuntimeException("No se ha encontrado la carrera seleccionada"));
                return new CarrerasUsuario(carrera, usuarioNuevo);
            }).collect(Collectors.toList());
            usuarioNuevo.setCarrerasUsuarioList(carreras);
        }
        LocalDate currentDate = LocalDate.now();
        Usuario usuarioGuardado = usuarioRepository.save(usuarioNuevo);
        //usuarioNuevo.setRegisteredAt(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return usuarioGuardado;
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
    public Optional<Usuario> getUsuarioDisponible(Integer idCarrera, Integer idRol) {
        return usuarioRepository.getSupervisorDisponible(idCarrera, idRol);
    }

    @Override
    public Optional<Usuario> getUsuarioPorRegistor(String registroAcademico) {
        return usuarioRepository.findByRegistroAcademico(registroAcademico);
    }

}
