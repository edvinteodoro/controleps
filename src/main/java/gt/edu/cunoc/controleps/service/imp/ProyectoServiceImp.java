/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.model.entity.CarrerasUsuario;
import gt.edu.cunoc.controleps.model.entity.Elemento;
import gt.edu.cunoc.controleps.model.entity.ElementosProyecto;
import gt.edu.cunoc.controleps.model.entity.EstadoEps;
import gt.edu.cunoc.controleps.model.entity.Etapa;
import gt.edu.cunoc.controleps.model.entity.EtapasProyecto;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.repository.CarreraUsuarioRepository;
import gt.edu.cunoc.controleps.repository.ElementoRepository;
import gt.edu.cunoc.controleps.repository.EstadoEpsRepository;
import gt.edu.cunoc.controleps.repository.EtapaRepository;
import gt.edu.cunoc.controleps.repository.ProyectoRepository;
import gt.edu.cunoc.controleps.service.ProyectoService;
import gt.edu.cunoc.controleps.service.StorageService;
import gt.edu.cunoc.controleps.service.UsuarioService;
import gt.edu.cunoc.controleps.utils.ProyectoUtils;
import gt.edu.cunoc.controleps.utils.RolUtils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    private final StorageService storageService;
    private final NotificationService notificacionService;
    private final EtapaRepository etapaRepository;
    private final ElementoRepository elementoRepository;

    public ProyectoServiceImp(ProyectoRepository proyectoRepository, UsuarioService usuarioService,
            EstadoEpsRepository estadoEpsRepository, CarreraUsuarioRepository carreraUsuarioRepository,
            NotificationService notificacionService, StorageService storageService,
            EtapaRepository etapaRepository, ElementoRepository elementoRepository) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioService = usuarioService;
        this.estadoEpsRepository = estadoEpsRepository;
        this.carreraUsuarioRepository = carreraUsuarioRepository;
        this.notificacionService = notificacionService;
        this.storageService = storageService;
        this.etapaRepository = etapaRepository;
        this.elementoRepository = elementoRepository;
    }

    @Override
    public ProyectoEps crearProyectoEps(ProyectoDto proyectoDto, String registroAcademico) throws Exception{
        //validar que no se alla excedido el limite de intentos y que no exista ningun proyecto activo.
        Usuario usuario = usuarioService.getUsuarioPorRegistor(registroAcademico)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        Optional<ProyectoEps> proyectoEps = proyectoRepository
                .getProyectoActivo(ProyectoUtils.ID_ESTADO_EPS_SOLICITUD, usuario.getIdUsuario());
        if (proyectoEps.isEmpty()) {
            CarrerasUsuario carreraUsuario = carreraUsuarioRepository.getCarreraUsuario(proyectoDto.getCarrera().getIdCarrera(), registroAcademico)
                    .orElseThrow(() -> new RuntimeException("No se encontro la carrera seleccionada"));
            CarrerasUsuario supervisor = this.carreraUsuarioRepository.getCarreraUsuarioSupervisor(RolUtils.ID_ROL_SUPERVISOR)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario supervisor para esta carrera"));
            Usuario secretaria = usuarioService.getSecretariaDisponible()
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario secretaria disponible"));
            
            ProyectoEps proyectoNuevo = crearProyecto(proyectoDto);
            proyectoNuevo.setIdCarrerasUsuarioFk(carreraUsuario);
            proyectoNuevo.setIdCarrerasSupervisorFk(supervisor);
            proyectoNuevo.setIdSecretariaFk(secretaria);

            return proyectoRepository.save(proyectoNuevo);
        } else {
            throw new RuntimeException("Ya existe un proyecto activo para esta carrera");
        }
    }

    @Override
    public List<ProyectoEps> getProyectosEps(String registroAcademico) {
        Usuario usuario = this.usuarioService.getUsuarioPorRegistor(registroAcademico)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario"));
        if (usuario.getIdRolFk().getIdRol().equals(RolUtils.ID_ROL_ESTUDIANTE)) {//estudiante
            return proyectoRepository.getProyectosUsuario(registroAcademico);
        } else if (usuario.getIdRolFk().getIdRol().equals(RolUtils.ID_ROL_SECRETARIA)) {
            return proyectoRepository.getProyectosSecretaria(registroAcademico);
        }
        return null;
    }

    @Override
    public ProyectoEps getProyectoById(Integer idProyecto, String nombreUsuario) {
        Usuario usuario = usuarioService.getUsuarioPorRegistor(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        return proyectoRepository.findByIdAnteproyecto(idProyecto)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario secretaria disponible"));
    }

    private ProyectoEps crearProyecto(ProyectoDto proyectoDto) throws Exception {
        Date fechaActual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        ProyectoEps proyectoNuevo = new ProyectoEps(proyectoDto);
        proyectoNuevo.setFechaInicio(fechaActual);
        EstadoEps estadoEps = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_SOLICITUD);
        Etapa etapa = etapaRepository.findByIdEtapa(ProyectoUtils.ID_ETAPA_SOLICITUD);
        EtapasProyecto etapaProyecto = new EtapasProyecto(fechaActual, estadoEps, etapa, proyectoNuevo);
        etapaProyecto.setEstadoFk(estadoEps);
        Elemento elementoAnteproyecto = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_ANTEPROYECTO);
        String anteproyectoUrl = storageService.saveFile(proyectoDto.getAnteproyecto());
        Elemento elementoConstanciaIns = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_CONSTANCIA_INSCRIPCION);
        String constanciaInsUrl = storageService.saveFile(proyectoDto.getConstanciaInscripcion());
        Elemento elementoConstanciaProp = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_CONSTANCIA_PROPEDEUTICO);
        String constanciaPropUrl = storageService.saveFile(proyectoDto.getConstanciaPropedeutico());
        Elemento elementoCertificadoNac = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_CERTIFICADO_NACIMIENTO);
        String certificadoNacUrl = storageService.saveFile(proyectoDto.getCertificadoNacimiento());
        Elemento elementoCartaAsesorSupervisor = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_CARTA_ASESOR_SUPERVISOR);
        String cartaAsesorSupervisorUrl = storageService.saveFile(proyectoDto.getCartaAsesorSupervisor());
        List<ElementosProyecto> elementosProyecto = new ArrayList<>();
        elementosProyecto.add(new ElementosProyecto(fechaActual, ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                anteproyectoUrl, elementoAnteproyecto, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(fechaActual, ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                constanciaInsUrl, elementoConstanciaIns, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(fechaActual, ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                constanciaPropUrl, elementoConstanciaProp, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(fechaActual, ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                certificadoNacUrl, elementoCertificadoNac, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(fechaActual, ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                cartaAsesorSupervisorUrl, elementoCartaAsesorSupervisor, etapaProyecto));
        etapaProyecto.setElementosProyectoList(elementosProyecto);
        proyectoNuevo.setEtapasProyectoList(Stream.of(etapaProyecto).collect(Collectors.toList()));
        proyectoNuevo.setIdEstadoEpsFk(estadoEps); 
        return proyectoNuevo;
    }

}
