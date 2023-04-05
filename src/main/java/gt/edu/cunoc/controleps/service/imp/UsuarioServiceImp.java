/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.ActivarUsuarioDto;
import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import gt.edu.cunoc.controleps.model.entity.Carrera;
import gt.edu.cunoc.controleps.model.entity.CarrerasUsuario;
import gt.edu.cunoc.controleps.model.entity.Rol;
import gt.edu.cunoc.controleps.model.entity.TokenConfirmacion;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.repository.CarreraRepository;
import gt.edu.cunoc.controleps.repository.TokenConfirmacionRepository;
import gt.edu.cunoc.controleps.repository.UsuarioRepository;
import gt.edu.cunoc.controleps.service.UsuarioService;
import gt.edu.cunoc.controleps.utils.FrontendUtils;
import gt.edu.cunoc.controleps.utils.RolUtils;
import gt.edu.cunoc.controleps.utils.UsuarioUtils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    private final NotificationService notificationService;
    private final TokenConfirmacionRepository tokenConfirmacionRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, RolServiceImp rolService,
            CarreraRepository carreraRepository, NotificationService notificationService,
            TokenConfirmacionRepository tokenConfirmacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
        this.carreraRepository = carreraRepository;
        this.notificationService = notificationService;
        this.tokenConfirmacionRepository = tokenConfirmacionRepository;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crearUsuario(UsuarioDto usuarioDto) throws Exception {
        usuarioDto.setEstadoCuenta(UsuarioUtils.USUARIO_INACTIVO);
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
        String token = generateToken();
        TokenConfirmacion tokenConfirmacion = new TokenConfirmacion(token, Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tokenConfirmacion.setIdUsuarioFk(usuarioNuevo);
        usuarioNuevo.setTokenConfirmacion(tokenConfirmacion);
        Usuario usuarioGuardado = usuarioRepository.save(usuarioNuevo);
        notificationService.sendNotification("edvinteodoro-gonzalezrafael@cunoc.edu.gt",
                "confirma tu usuario", FrontendUtils.URL_ACTIVAR_USUARIO + token);
        System.out.println("link: " + FrontendUtils.URL_ACTIVAR_USUARIO + token);
        return usuarioGuardado;
    }

    @Override
    public Usuario actualizarUsuario(UsuarioDto usuarioDto) {
        return null;
    }

    @Override
    public Optional<Usuario> getSupervisorDisponible(Integer idCarrera) {
        return usuarioRepository.getSupervisorDisponible(idCarrera, RolUtils.ID_ROL_SUPERVISOR);
    }

    @Override
    public Optional<Usuario> getUsuarioPorRegistor(String registroAcademico) {
        return usuarioRepository.findByRegistroAcademico(registroAcademico);
    }

    @Override
    public Optional<Usuario> getSecretariaDisponible() {
        return usuarioRepository.getSecretariaDisponible(RolUtils.ID_ROL_SECRETARIA);
    }

    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public Usuario activarUsuario(ActivarUsuarioDto activarUsuarioDto) throws Exception {
        Optional<TokenConfirmacion> token = tokenConfirmacionRepository.findByToken(activarUsuarioDto.getToken());
        if (token.isPresent()) {
            Usuario usuario = token.get().getIdUsuarioFk();
            if (!validarUsuario(usuario, activarUsuarioDto.getUsuario())) {
                throw new Exception("Usuario invalido.");
            }
            if (!activarUsuarioDto.getPassword1().equals(activarUsuarioDto.getPassword2())) {
                throw new Exception("Las contraseñas no coinciden.");
            }
            if(!activarUsuarioDto.getPassword1().matches(UsuarioUtils.REGEX)){
                throw new Exception("Las contraseña no es lo suficientemente fuerte.");
            }
            usuario.setPassword(new BCryptPasswordEncoder().encode(activarUsuarioDto.getPassword1()));
            usuario.setEstadoCuenta(UsuarioUtils.USUARIO_ACTIVO);
            usuarioRepository.save(usuario);
            return usuario;
        } else {
            throw new Exception("No se ha encontrado el token");
        }
    }

    private boolean validarUsuario(Usuario usuario, String nombreUsuario) {
        if (usuario.getRegistroAcademico() != null && usuario.getRegistroAcademico().equals(nombreUsuario)) {
            return Boolean.TRUE;
        } else if (usuario.getNumeroColegiado() != null && usuario.getNumeroColegiado().equals(nombreUsuario)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
