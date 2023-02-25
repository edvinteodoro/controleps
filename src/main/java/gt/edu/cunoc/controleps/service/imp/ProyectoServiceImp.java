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
import org.springframework.stereotype.Service;

/**
 *
 * @author edvin
 */
@Service
public class ProyectoServiceImp implements ProyectoService{
    private final ProyectoRepository proyectoRepository;
    private final UsuarioService usuarioService;
    private final CarreraRepository carreraRepository;
    
    public ProyectoServiceImp(ProyectoRepository proyectoRepository, UsuarioService usuarioService,CarreraRepository carreraRepository) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioService = usuarioService;
        this.carreraRepository = carreraRepository;
    }

    @Override
    public ProyectoEps crearProyectoEps(ProyectoDto proyectoDto, String usuario){
        Carrera carrera=carreraRepository.getCarreraEstudiante(proyectoDto.getIdCarrera(), usuario)
                .orElseThrow(() -> new RuntimeException("No se encontro la carrera seleccionada"));
        //Usuario usuario=usuarioService.getUsuarioPorRegistor(registroAcademico);
        Integer idCarrera=1;//comprobar si el usuario si tiene la carrera asignada
        Integer idRol=2; //comprobar si el usuario tiene el rol especifico
        Usuario supervisor = usuarioService.getUsuarioDisponible(idCarrera,idRol);
        System.out.println("supervisor: "+ supervisor.getNombres());
        return null;
    }


}
