/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.EvaluacionDto;
import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.model.entity.CarrerasUsuario;
import gt.edu.cunoc.controleps.model.entity.Comentario;
import gt.edu.cunoc.controleps.model.entity.Elemento;
import gt.edu.cunoc.controleps.model.entity.ElementosProyecto;
import gt.edu.cunoc.controleps.model.entity.EstadoEps;
import gt.edu.cunoc.controleps.model.entity.Etapa;
import gt.edu.cunoc.controleps.model.entity.EtapasProyecto;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.repository.CarreraUsuarioRepository;
import gt.edu.cunoc.controleps.repository.ComentarioRepository;
import gt.edu.cunoc.controleps.repository.ElementoRepository;
import gt.edu.cunoc.controleps.repository.EstadoEpsRepository;
import gt.edu.cunoc.controleps.repository.EtapaProyectoRepository;
import gt.edu.cunoc.controleps.repository.EtapaRepository;
import gt.edu.cunoc.controleps.repository.ProyectoRepository;
import gt.edu.cunoc.controleps.service.DocumentoService;
import gt.edu.cunoc.controleps.service.ProyectoService;
import gt.edu.cunoc.controleps.service.StorageService;
import gt.edu.cunoc.controleps.service.UsuarioService;
import gt.edu.cunoc.controleps.utils.ProyectoUtils;
import gt.edu.cunoc.controleps.utils.RolUtils;
import jakarta.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
    private final NotificationService notificacionService;
    private final EtapaRepository etapaRepository;
    private final ElementoRepository elementoRepository;
    private final DocumentoService documentoService;
    private final EtapaProyectoRepository etapasProyectoRepository;
    private final ComentarioRepository comentarioRepository;
    private final StorageService storageService;

    public ProyectoServiceImp(ProyectoRepository proyectoRepository, UsuarioService usuarioService,
            EstadoEpsRepository estadoEpsRepository, CarreraUsuarioRepository carreraUsuarioRepository,
            NotificationService notificacionService, StorageService storageService,
            EtapaRepository etapaRepository, ElementoRepository elementoRepository,
            DocumentoService documentoService, EtapaProyectoRepository etapasProyectoRepository,
            ComentarioRepository comentarioRepository) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioService = usuarioService;
        this.estadoEpsRepository = estadoEpsRepository;
        this.carreraUsuarioRepository = carreraUsuarioRepository;
        this.notificacionService = notificacionService;
        this.etapaRepository = etapaRepository;
        this.elementoRepository = elementoRepository;
        this.documentoService = documentoService;
        this.etapasProyectoRepository = etapasProyectoRepository;
        this.comentarioRepository = comentarioRepository;
        this.storageService = storageService;
    }

    @Override
    @Transactional
    public ProyectoEps crearProyectoEps(ProyectoDto proyectoDto, String registroAcademico) throws Exception {
        //validar que no se alla excedido el limite de intentos y que no exista ningun proyecto activo.
        Usuario usuario = usuarioService.getUsuarioPorRegistor(registroAcademico)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        Optional<ProyectoEps> proyectoEps = proyectoRepository
                .getProyectoActivo(ProyectoUtils.ID_ESTADO_EPS_ACTIVO, usuario.getIdUsuario());
        if (proyectoEps.isEmpty()) {
            CarrerasUsuario carreraUsuario = carreraUsuarioRepository.getCarreraUsuario(proyectoDto.getCarrera().getIdCarrera(), registroAcademico)
                    .orElseThrow(() -> new RuntimeException("No se encontro la carrera seleccionada"));
            /*CarrerasUsuario supervisor = this.carreraUsuarioRepository.getCarreraUsuarioSupervisor(RolUtils.ID_ROL_SUPERVISOR)
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario supervisor para esta carrera"));*/
            Usuario secretaria = usuarioService.getSecretariaDisponible()
                    .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario secretaria disponible"));
            Optional<Usuario> asesor = usuarioService.getUsuarioPorColegiado(proyectoDto.getAsesor().getNumeroColegiado());
            ProyectoEps proyectoNuevo = crearProyecto(proyectoDto);
            proyectoNuevo.setIdCarrerasUsuarioFk(carreraUsuario);
            //proyectoNuevo.setIdCarrerasSupervisorFk(supervisor);
            proyectoNuevo.setIdSecretariaFk(secretaria);
            proyectoNuevo.setColegiadoAsesor(proyectoDto.getAsesor().getNumeroColegiado());
            if (asesor.isEmpty()) {
                Usuario asesorNuevo = usuarioService.crearAsesor(proyectoDto.getAsesor());
                proyectoNuevo.setIdAsesorFk(asesorNuevo);
            }
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
            return proyectoRepository.getProyectosSecretaria(registroAcademico,
                    ProyectoUtils.ID_ETAPA_REVISION_SECRETARIA, ProyectoUtils.ID_ESTADO_EPS_ACTIVO);
        } else if (usuario.getIdRolFk().getIdRol().equals(RolUtils.ID_ROL_SUPERVISOR)) {
            return proyectoRepository.getProyectosSupervisor(registroAcademico);
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
        ProyectoEps proyectoNuevo = new ProyectoEps(proyectoDto);
        proyectoNuevo.setFechaInicio(getFechaActual());
        EstadoEps estadoEps = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_ACTIVO);
        Etapa etapa = etapaRepository.findByIdEtapa(ProyectoUtils.ID_ETAPA_REVISION_SECRETARIA);
        EtapasProyecto etapaProyecto = new EtapasProyecto(getFechaActual(), estadoEps, etapa, proyectoNuevo);
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
        Elemento elementoCartaEstudianteSupervisor = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_CARTA_ESTUDIANTE_SUPERVISOR);
        Map<String, Object> data = new HashMap<>();
        data.put("${nombre}", "Edvin Teodoro Gonzalez Rafael");
        data.put("${registroAcademico}", "201630873");
        data.put("${dpi}", "3218 35905 1323");
        //String url = storageService.getFileUrl(elementoCartaEstudianteSupervisor.getTemplate());
        //InputStream input = new URL(url).openStream();
        //MultipartFile file = poiUtils.generarDocumento(data, input, "PDF", "cartaEstudianteSupervisor.docx");
        //String cartaEstudianteSupervisorUrl = storageService.saveFile(file);
        List<ElementosProyecto> elementosProyecto = new ArrayList<>();
        elementosProyecto.add(new ElementosProyecto(getFechaActual(), ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                anteproyectoUrl, elementoAnteproyecto, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(getFechaActual(), ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                constanciaInsUrl, elementoConstanciaIns, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(getFechaActual(), ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                constanciaPropUrl, elementoConstanciaProp, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(getFechaActual(), ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                certificadoNacUrl, elementoCertificadoNac, etapaProyecto));
        elementosProyecto.add(new ElementosProyecto(getFechaActual(), ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
                cartaAsesorSupervisorUrl, elementoCartaAsesorSupervisor, etapaProyecto));
        //elementosProyecto.add(new ElementosProyecto(getFechaActual(), ProyectoUtils.ESTADO_ACTIVO_ELEMENTO_PROYECTO,
        //        cartaEstudianteSupervisorUrl, elementoCartaEstudianteSupervisor, etapaProyecto));
        etapaProyecto.setElementosProyectoList(elementosProyecto);
        proyectoNuevo.setEtapasProyectoList(Stream.of(etapaProyecto).collect(Collectors.toList()));
        proyectoNuevo.setIdEstadoEpsFk(estadoEps);
        return proyectoNuevo;
    }

    @Override
    @Transactional
    public void aprobacionSecretaria(Integer idProyecto, String registroAcademico) throws Exception {
        Usuario usuarioSecretaria = usuarioService.getUsuarioPorRegistor(registroAcademico)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        ProyectoEps proyectoEps = proyectoRepository
                .findByIdAnteproyecto(idProyecto)
                .orElseThrow(() -> new RuntimeException("No se encontro el proyecto"));
        EtapasProyecto etapaProyecto = etapasProyectoRepository
                .getEtapasProyectoPorEtapa(proyectoEps.getIdAnteproyecto(), ProyectoUtils.ID_ETAPA_REVISION_SECRETARIA)
                .orElseThrow(() -> new RuntimeException("No se encontro el proyecto"));
        if (etapaProyecto.getEstadoFk().getIdEstadoEps().equals(ProyectoUtils.ID_ESTADO_EPS_ACTIVO)) {
            if (proyectoEps.getIdSecretariaFk() == usuarioSecretaria) {
                EstadoEps estadoEpsFinalizado = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_FINALIZADO);
                EstadoEps estadoEpsNuevo = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_ACTIVO);
                etapaProyecto.setEstadoFk(estadoEpsFinalizado);
                Etapa etapa = etapaRepository.findByIdEtapa(ProyectoUtils.ID_ETAPA_REVISION_SUPERVISOR);
                EtapasProyecto nuevaEtapaProyecto = new EtapasProyecto(getFechaActual(), estadoEpsNuevo, etapa, proyectoEps);
                CarrerasUsuario supervisor = this.carreraUsuarioRepository.getCarreraUsuarioSupervisor(RolUtils.ID_ROL_SUPERVISOR)
                        .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario supervisor para esta carrera"));
                proyectoEps.setIdCarrerasSupervisorFk(supervisor);
                proyectoRepository.save(proyectoEps);
                etapasProyectoRepository.save(etapaProyecto);
                etapasProyectoRepository.save(nuevaEtapaProyecto);
            } else {
                throw new Exception("Usuario no autorizado para aprobar documentos"); //usuario no autorizado
            }
        } else {
            throw new Exception("No se puede aprobar el proyecto"); //usuario no autorizado
        }
    }

    @Override
    @Transactional
    public void aprobacionSupervisor(Integer idProyecto, String registroAcademico) throws Exception {
        Usuario usuarioSupervisor = usuarioService.getUsuarioPorRegistor(registroAcademico)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        ProyectoEps proyectoEps = proyectoRepository
                .findByIdAnteproyecto(idProyecto)
                .orElseThrow(() -> new RuntimeException("No se encontro el proyecto"));
        EtapasProyecto etapaProyecto = etapasProyectoRepository
                .getEtapasProyectoPorEtapa(proyectoEps.getIdAnteproyecto(), ProyectoUtils.ID_ETAPA_REVISION_SUPERVISOR)
                .orElseThrow(() -> new RuntimeException("No se encontro el proyecto"));
        if (etapaProyecto.getEstadoFk().getIdEstadoEps().equals(ProyectoUtils.ID_ESTADO_EPS_ACTIVO)) {
            if (proyectoEps.getIdCarrerasSupervisorFk().getIdUsuarioFk() == usuarioSupervisor) {
                EstadoEps estadoEpsFinalizado = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_FINALIZADO);
                EstadoEps estadoEpsNuevo = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_ACTIVO);
                etapaProyecto.setEstadoFk(estadoEpsFinalizado);
                Etapa etapa = etapaRepository.findByIdEtapa(ProyectoUtils.ID_ETAPA_DEFINIR_EVALUACION);
                EtapasProyecto nuevaEtapaProyecto = new EtapasProyecto(getFechaActual(), estadoEpsNuevo, etapa, proyectoEps);
                etapasProyectoRepository.save(etapaProyecto);
                etapasProyectoRepository.save(nuevaEtapaProyecto);
            } else {
                throw new Exception("Usuario no autorizado para aprobar documentos"); //usuario no autorizado
            }
        } else {
            throw new Exception("La etapa de aprovacion de supervisor no esta disponilbe"); //usuario no autorizado
        }
    }

    @Override
    public EtapasProyecto getEtapaProyectoActiva(Integer idProyecto) throws Exception {
        EtapasProyecto etapaProyecto = etapasProyectoRepository
                .getEtapasProyectoActivo(idProyecto, ProyectoUtils.ID_ESTADO_EPS_ACTIVO)
                .orElseThrow(() -> new RuntimeException("No se encontro etapa activa de proyecto"));
        return etapaProyecto;
    }

    @Override
    public Comentario comentarProyecto(Integer idProyecto, String nombreUsuario, String texto) throws Exception {
        Usuario usuario = usuarioService.getUsuarioPorRegistor(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        ProyectoEps proyectoEps = proyectoRepository
                .findByIdAnteproyecto(idProyecto)
                .orElseThrow(() -> new RuntimeException("No se encontro el proyecto"));
        EtapasProyecto etapaProyecto = etapasProyectoRepository
                .getEtapasProyectoActivo(idProyecto, ProyectoUtils.ID_ESTADO_EPS_ACTIVO)
                .orElseThrow(() -> new RuntimeException("No se encontro etapa activa de proyecto"));
        Comentario comentario = new Comentario(texto, getFechaActual(), usuario, etapaProyecto);
        return this.comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> getComentariosEtapa(Integer idProyecto, Integer idEtapa) throws Exception {
        return this.comentarioRepository.getComentariosEtapa(idProyecto, idEtapa);
    }

    @Override
    @Transactional
    public void definirEvaluacion(Integer idProyecto, String registroAcademico, EvaluacionDto evaluacionDto) throws Exception {
        Usuario usuario = usuarioService.getUsuarioPorRegistor(registroAcademico)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        ProyectoEps proyectoEps = proyectoRepository
                .findByIdAnteproyecto(idProyecto)
                .orElseThrow(() -> new RuntimeException("No se encontro el proyecto"));
        EtapasProyecto etapaProyecto = etapasProyectoRepository
                .getEtapasProyectoPorEtapa(proyectoEps.getIdAnteproyecto(), ProyectoUtils.ID_ETAPA_DEFINIR_EVALUACION)
                .orElseThrow(() -> new RuntimeException("No se encontro etapa de proyecto"));
        Usuario estudiante = proyectoEps.getIdCarrerasUsuarioFk().getIdUsuarioFk();
        if (etapaProyecto.getEstadoFk().getIdEstadoEps().equals(ProyectoUtils.ID_ESTADO_EPS_ACTIVO)) {
            if (proyectoEps.getIdCarrerasSupervisorFk().getIdUsuarioFk() == usuario) {
                EstadoEps estadoEpsFinalizado = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_FINALIZADO);
                EstadoEps estadoEpsNuevo = estadoEpsRepository.findByIdEstadoEps(ProyectoUtils.ID_ESTADO_EPS_ACTIVO);
                etapaProyecto.setEstadoFk(estadoEpsFinalizado);
                Etapa etapa = etapaRepository.findByIdEtapa(ProyectoUtils.ID_ETAPA_EVALUAR_PROYECTO);
                EtapasProyecto nuevaEtapaProyecto = new EtapasProyecto(getFechaActual(), estadoEpsNuevo, etapa, proyectoEps);
                HashMap<Integer, String> informacion = new HashMap<>();
                // Populate the HashMap
                informacion.put(ProyectoUtils.ID_ELEMENTO_FECHA_EVALUACION, formatDate(evaluacionDto.getFecha()));
                informacion.put(ProyectoUtils.ID_ELEMENTO_HORA_EVALUACION, formatTime(evaluacionDto.getFecha()));
                informacion.put(ProyectoUtils.ID_ELEMENTO_NOMBRE_SUPERVISOR, usuario.getNombreCompleto());
                informacion.put(ProyectoUtils.ID_ELEMENTO_NOMBRE_ASESOR, proyectoEps.getIdAsesorFk().getNombreCompleto());
                informacion.put(ProyectoUtils.ID_ELEMENTO_NOMBRE_COORDINADOR, "Francisco Lopez");
                informacion.put(ProyectoUtils.ID_ELEMENTO_CARRERA, proyectoEps.getIdCarrerasUsuarioFk().getIdCarreraFk().getTitulo());
                informacion.put(ProyectoUtils.ID_ELEMENTO_NUMERO_CARRERA, "02-2022");
                informacion.put(ProyectoUtils.ID_ELEMENTO_DIRECCION, "Quetzaltenango");
                informacion.put(ProyectoUtils.ID_ELEMENTO_CARNE, usuario.getDpi());
                informacion.put(ProyectoUtils.ID_ELEMENTO_NOMBRE_ESTUDIANTE, estudiante.getNombreCompleto());
                informacion.put(ProyectoUtils.ID_ELEMENTO_REGISTRO_ACADEMICO, estudiante.getRegistroAcademico());
                informacion.put(ProyectoUtils.ID_ELEMENTO_TITULO_PROYECTO, proyectoEps.getTitulo());
                informacion.put(ProyectoUtils.ID_ELEMENTO_DIA, getDia(evaluacionDto.getFecha()));
                if (evaluacionDto.getRepresentante() != null) {
                    informacion.put(ProyectoUtils.ID_ELEMENTO_NOMBRE_REPRESENTANTE, evaluacionDto.getRepresentante());
                }
                List<ElementosProyecto> elementosProyecto = insertarElementos(etapaProyecto.getIdEtapaFk(), informacion, etapaProyecto);
                Elemento convocatoriaOficio = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_CONVOCATORIA_OFICIO);
                String urlConvocatoria=documentoService.generarConvocatoriaEvaluacion(elementosProyecto);
                ElementosProyecto elementoProyecto = new ElementosProyecto(urlConvocatoria,
                        getFechaActual(), convocatoriaOficio, etapaProyecto);
                elementosProyecto.add(elementoProyecto);
                etapaProyecto.setElementosProyectoList(elementosProyecto);
                etapasProyectoRepository.save(etapaProyecto);
                etapasProyectoRepository.save(nuevaEtapaProyecto);
                this.notificacionService.enviarConvocatoria("edvinteodoro-gonzalezrafael@cunoc.edu.gt", "Convocatoria",
                        "Convocatoria Evaluacion", storageService.getFileUrl(urlConvocatoria));
            } else {
                throw new Exception("Usuario no autorizado para aprobar documentos"); //usuario no autorizado
            }
        } else {
            throw new Exception("No se puede aprobar el proyecto"); //usuario no autorizado
        }
    }

    private List<ElementosProyecto> insertarElementos(Etapa etapa, HashMap<Integer, String> valores, EtapasProyecto etapaProyecto) {
        List<ElementosProyecto> elementosProyecto = new ArrayList<>();
        etapa.getElementoList().forEach(elemento -> {
            if (valores.get(elemento.getIdElemento()) != null) {
                ElementosProyecto elementoProyecto = new ElementosProyecto(valores.get(elemento.getIdElemento()), getFechaActual(), elemento, etapaProyecto);
                elementosProyecto.add(elementoProyecto);
            }
        });
        return elementosProyecto;
    }

    private Date getFechaActual() {
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM yyyy", new Locale("es"));
        return dateFormat.format(date);
    }

    public static String getDia(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        return dateFormat.format(date);
    }

    private static String formatTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", new Locale("es"));
        return timeFormat.format(date);
    }

}
