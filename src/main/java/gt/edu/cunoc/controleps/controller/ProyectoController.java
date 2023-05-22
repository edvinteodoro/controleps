/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.ComentarioDto;
import gt.edu.cunoc.controleps.model.dto.EtapaDto;
import gt.edu.cunoc.controleps.model.dto.EvaluacionDto;
import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.model.entity.Comentario;
import gt.edu.cunoc.controleps.model.entity.EtapasProyecto;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import gt.edu.cunoc.controleps.service.CarreraService;
import gt.edu.cunoc.controleps.service.ProyectoService;
import gt.edu.cunoc.controleps.service.StorageService;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edvin
 */
@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;
    private final CarreraService carreraService;
    private final StorageService storageService;

    public ProyectoController(ProyectoService proyectoService, CarreraService carreraService,
            StorageService storageService) {
        this.proyectoService = proyectoService;
        this.carreraService = carreraService;
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity crearProyecto(@ModelAttribute ProyectoDto proyectoDto, Principal principal) {
        try {
            ProyectoDto proyecto = new ProyectoDto(proyectoService.crearProyectoEps(proyectoDto, principal.getName()));
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getProyectos(Principal principal) {
        try {

            List<ProyectoDto> proyectosDto = proyectoService.getProyectosEps(principal.getName()).stream()
                    .map(proyecto -> new ProyectoDto(proyecto)).collect(Collectors.toList());
            return ResponseEntity.ok(proyectosDto);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PostMapping("/{idProyecto}/solicitarCambios")
    public ResponseEntity solicitarCambios(){
    
    }

    @PostMapping("/{idProyecto}/aprobarSecretaria")
    public ResponseEntity aprobarSecretaria(@PathVariable(value = "idProyecto") Integer idProyecto, Principal principal) {
        try {
            proyectoService.aprobacionSecretaria(idProyecto, principal.getName());
            return ResponseEntity.ok("Listo");
        } catch (Exception e) {
            System.out.println("error: " + e + "--------- " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{idProyecto}/aprobarSupervisor")
    public ResponseEntity aprobarSupervisor(@PathVariable(value = "idProyecto") Integer idProyecto, Principal principal) {
        try {
            proyectoService.aprobacionSupervisor(idProyecto, principal.getName());
            return ResponseEntity.ok("Listo");
        } catch (Exception e) {
            System.out.println("error: " + e + "--------- " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PostMapping("/{idProyecto}/definirEvaluacion")
    public ResponseEntity definirEvaluacion(@PathVariable(value = "idProyecto") Integer idProyecto, Principal principal,@RequestBody EvaluacionDto evaluacionDto) {
        try {
            proyectoService.definirEvaluacion(idProyecto,principal.getName(), evaluacionDto);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            System.out.println("error: " + e + "--------- " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{idProyecto}")
    public ResponseEntity getProyecto(@PathVariable(value = "idProyecto") Integer idProyecto, Principal principal) {
        try {
            ProyectoEps proyecto = proyectoService.getProyectoById(idProyecto, principal.getName());
            return ResponseEntity.ok(new ProyectoDto(proyecto));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{idProyecto}/etapaActiva")
    public ResponseEntity getEtapaProyecto(@PathVariable(value = "idProyecto") Integer idProyecto) {
        try {
            EtapasProyecto etapa = proyectoService.getEtapaProyectoActiva(idProyecto);
            return ResponseEntity.ok(new EtapaDto(etapa));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{idProyecto}/comentar")
    public ResponseEntity comentarProyecto(@PathVariable(value = "idProyecto") Integer idProyecto, Principal principal, @RequestBody ComentarioDto comentarioDto) {
        try {
            Comentario comentario = proyectoService.comentarProyecto(idProyecto, principal.getName(), comentarioDto.getTexto());
            return ResponseEntity.ok(new ComentarioDto(comentario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{idProyecto}/etapas")
    public ResponseEntity getEtapasProyecto(@PathVariable(value = "idProyecto") Integer idProyecto, Principal principal) {
        try {
            ProyectoEps proyecto = proyectoService.getProyectoById(idProyecto, principal.getName());
            List<EtapaDto> etapas = proyecto.getEtapasProyectoList().stream()
                    .map(etapaProyecto -> new EtapaDto(etapaProyecto)).collect(Collectors.toList());
            //Comentario comentario=proyectoService.comentarProyecto(idProyecto, principal.getName(), texto);
            return ResponseEntity.ok(etapas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping("/{idProyecto}/etapa/{idEtapa}/comentarios")
    public ResponseEntity getComentariosEtapa(@PathVariable(value = "idProyecto") Integer idProyecto,
            @PathVariable(value = "idEtapa") Integer idEtapa, Principal principal) {
        try {
            List<ComentarioDto> comentarios = this.proyectoService.getComentariosEtapa(idProyecto, idEtapa).stream()
                    .map(comentario -> new ComentarioDto(comentario)).collect(Collectors.toList());
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
