/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.service;

import gt.edu.cunoc.controleps.model.dto.EvaluacionDto;
import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.model.entity.Comentario;
import gt.edu.cunoc.controleps.model.entity.EtapasProyecto;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import java.util.List;
/**
 *
 * @author edvin
 */
public interface ProyectoService {
    public ProyectoEps crearProyectoEps(ProyectoDto proyectoDto, String usuario) throws Exception;
    public List<ProyectoEps> getProyectosEps(String registroAcademico);
    public ProyectoEps getProyectoById(Integer idProyecto,String nombreUsuario);
    public EtapasProyecto getEtapaProyectoActiva(Integer idProyecto) throws Exception;
    public void aprobacionSecretaria(Integer idProyecto,String registroAcademico) throws Exception;
    public Comentario comentarProyecto(Integer idProyecto,String nombreUsuario,String texto) throws Exception;
    public void aprobacionSupervisor(Integer idProyecto, String registroAcademico) throws Exception;
    public List<Comentario> getComentariosEtapa(Integer idProyecto, Integer idEtapa) throws Exception;
    public void definirEvaluacion(Integer idProyecto, String registroAcademico, EvaluacionDto evaluacionDto) throws Exception;
}
