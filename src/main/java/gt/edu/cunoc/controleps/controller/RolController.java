/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.RolDto;
import gt.edu.cunoc.controleps.service.RolService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edvin
 */
@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }
    
    @GetMapping
    //add restrictions to the respective roles
    private ResponseEntity<List<RolDto>> getRoles(){
        List<RolDto> carreraList = rolService.getAll().stream()
        .map(rol -> new RolDto(rol))
        .collect(Collectors.toList());
        return ResponseEntity.ok(carreraList);
    }
    
    @PostMapping
    //add restrictions to the respective roles
    private ResponseEntity<RolDto> crearRol(@RequestBody RolDto rolDto){
        return ResponseEntity.ok(new RolDto(rolService.crearRol(rolDto)));
    }
    
    @PutMapping
    private ResponseEntity<RolDto> actualizarRol(@RequestBody RolDto rolDto){
        return ResponseEntity.ok(new RolDto(rolService.actualizarRol(rolDto)));
    }
    
}
