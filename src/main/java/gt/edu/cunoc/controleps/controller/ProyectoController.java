/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
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
            System.out.println("error: " + e.getMessage());
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
}
