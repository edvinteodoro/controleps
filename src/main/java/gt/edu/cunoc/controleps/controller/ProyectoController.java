/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.service.CarreraService;
import gt.edu.cunoc.controleps.service.ProyectoService;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    public ProyectoController(ProyectoService proyectoService, CarreraService carreraService) {
        this.proyectoService = proyectoService;
        this.carreraService = carreraService;
    }

    @GetMapping
    public ResponseEntity proyectos() {
        try {

        } catch (Exception e) {
        }
        return null;
    }

    @PostMapping
    public ResponseEntity crearProyecto(@ModelAttribute ProyectoDto proyectoDto, Principal principal) {
        try {
            System.out.println("titulo: " + proyectoDto.getTitulo());
            //System.out.println("carrera: "+proyectoDto.getCarrera().getTitulo());
            System.out.println("inscripcion: " + proyectoDto.getConstanciaInscripcion().getName());
            ProyectoDto proyecto = new ProyectoDto(proyectoService.crearProyectoEps(proyectoDto, principal.getName()));
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
