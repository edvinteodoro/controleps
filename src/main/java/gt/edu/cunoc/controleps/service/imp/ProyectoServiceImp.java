/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.model.entity.Carrera;
import gt.edu.cunoc.controleps.model.entity.CarrerasUsuario;
import gt.edu.cunoc.controleps.model.entity.EstadoEps;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.repository.CarreraRepository;
import gt.edu.cunoc.controleps.repository.CarreraUsuarioRepository;
import gt.edu.cunoc.controleps.repository.EstadoEpsRepository;
import gt.edu.cunoc.controleps.repository.ProyectoRepository;
import gt.edu.cunoc.controleps.service.ProyectoService;
import gt.edu.cunoc.controleps.service.UsuarioService;
import gt.edu.cunoc.controleps.utils.ProyectoUtils;
import gt.edu.cunoc.controleps.utils.RolUtils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

/**
 *
 * @author edvin
 */
@Service
public class ProyectoServiceImp implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final UsuarioService usuarioService;
    private final EstadoEpsRepository estadoEpsRepository;
    private final CarreraUsuarioRepository carreraUsuarioRepository;
    private final NotificationService notificacionService;

    public ProyectoServiceImp(ProyectoRepository proyectoRepository, UsuarioService usuarioService,
            EstadoEpsRepository estadoEpsRepository, CarreraUsuarioRepository carreraUsuarioRepository,
            NotificationService notificacionService) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioService = usuarioService;
        this.estadoEpsRepository = estadoEpsRepository;
        this.carreraUsuarioRepository = carreraUsuarioRepository;
        this.notificacionService = notificacionService;
    }

    @Override
    public ProyectoEps crearProyectoEps(ProyectoDto proyectoDto, String registroAcademico) {
        //validar que no se alla excedido el limite de intentos y que no exista ningun proyecto activo.
        Usuario usuario = usuarioService.getUsuarioPorRegistor(registroAcademico).orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        Optional<ProyectoEps> proyectoEps = proyectoRepository.getProyectoActivo(ProyectoUtils.ID_ESTADO_EPS_ACTIVO, usuario.getIdUsuario());
        if (proyectoEps.isEmpty()) {
            CarrerasUsuario carreraUsuario = carreraUsuarioRepository.getCarreraUsuario(proyectoDto.getCarrera().getIdCarrera(), registroAcademico)
                    .orElseThrow(() -> new RuntimeException("No se encontro la carrera seleccionada"));
            CarrerasUsuario supervisor = this.carreraUsuarioRepository.getCarreraUsuarioSupervisor(RolUtils.ID_ROL_SUPERVISOR)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario supervisor para esta carrera"));
            Usuario secretaria = usuarioService.getSecretariaDisponible()
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario secretaria disponible"));
            EstadoEps estadoEps= this.estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_ACTIVO);
            System.out.println("supervisor: " + supervisor.getIdUsuarioFk().getNombres());
            System.out.println("secretaria: " + secretaria.getNombres());
            LocalDate fechaActual = LocalDate.now();
            ProyectoEps proyectoNuevo = new ProyectoEps(proyectoDto);            
            proyectoNuevo.setFechaInicio(Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            proyectoNuevo.setIdCarrerasUsuarioFk(carreraUsuario);
            proyectoNuevo.setIdCarrerasSupervisorFk(supervisor);
            proyectoNuevo.setIdSecretariaFk(secretaria);
            proyectoNuevo.setIdEstadoEpsFk(estadoEps);
            return proyectoRepository.save(proyectoNuevo);
        } else {
            throw new RuntimeException("Ya existe un proyecto activo para esta carrera");
        }
    }

    @Override
    public List<ProyectoEps> getProyectoEps(String usuario) {
        return proyectoRepository.getProyectosUsuario(usuario);
    }

}
