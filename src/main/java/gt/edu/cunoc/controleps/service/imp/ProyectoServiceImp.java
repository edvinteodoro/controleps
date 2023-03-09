/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.model.entity.Carrera;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.repository.CarreraRepository;
import gt.edu.cunoc.controleps.repository.ProyectoRepository;
import gt.edu.cunoc.controleps.service.ProyectoService;
import gt.edu.cunoc.controleps.service.UsuarioService;
import gt.edu.cunoc.controleps.utils.ProyectoUtils;
import gt.edu.cunoc.controleps.utils.RolUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author edvin
 */
@Service
public class ProyectoServiceImp implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final UsuarioService usuarioService;
    private final CarreraRepository carreraRepository;

    public ProyectoServiceImp(ProyectoRepository proyectoRepository, UsuarioService usuarioService, CarreraRepository carreraRepository) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioService = usuarioService;
        this.carreraRepository = carreraRepository;
    }

    @Override
    public ProyectoEps crearProyectoEps(ProyectoDto proyectoDto, String registroAcademico) {
        //validar que no se alla excedido el limite de intentos y que no exista ningun proyecto activo.
        Usuario usuario = usuarioService.getUsuarioPorRegistor(registroAcademico).orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        Optional<ProyectoEps> proyectoEps = proyectoRepository.getProyectoActivo(ProyectoUtils.ID_ESTADO_EPS_ACTIVO, usuario.getIdUsuario());
        if (proyectoEps.isEmpty()) {
            Carrera carrera = carreraRepository.getCarreraEstudiante(proyectoDto.getCarrera().getIdCarrera(), registroAcademico)
                    .orElseThrow(() -> new RuntimeException("No se encontro la carrera seleccionada"));
            Usuario supervisor = usuarioService.getUsuarioDisponible(proyectoDto.getCarrera().getIdCarrera(), RolUtils.ID_ROL_SUPERVISOR)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario supervisor para esta carrera"));
            Usuario secretaria = usuarioService.getUsuarioDisponible(proyectoDto.getCarrera().getIdCarrera(), RolUtils.ID_ROL_SECRETARIA)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario secretaria disponible"));

            System.out.println("supervisor: " + supervisor.getNombres());
        }
        return null;
    }

    @Override
    public List<ProyectoEps> getProyectoEps(String usuario) {
        return proyectoRepository.getProyectosUsuario(usuario);
    }

}
