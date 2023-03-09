/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import gt.edu.cunoc.controleps.service.UsuarioService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
        
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        List<UsuarioDto> userList = usuarioService.getAll().stream()
                .map(usuario -> new UsuarioDto(usuario)).collect(Collectors.toList());
        return ResponseEntity.ok(userList); 
    }
    
    @GetMapping("/actual/carreras")
    public ResponseEntity getCarrerasUsuario(Principal principal){
        try {
            UsuarioDto usuario = new UsuarioDto(
                    this.usuarioService.getUsuarioPorRegistor(principal.getName())
            .orElseThrow(()-> new RuntimeException("No se pudo encontrar el usuario logeado")));  
            return ResponseEntity.ok(usuario.getCarreras());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity crearUsuario(@RequestBody UsuarioDto usuarioDto){
        try {
            return ResponseEntity.ok(new UsuarioDto(usuarioService.crearUsuario(usuarioDto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    @PutMapping
    @PreAuthorize("hasRole('ROLE_Secretaria')")
    public ResponseEntity<UsuarioDto> actualizarUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(new UsuarioDto(usuarioService.crearUsuario(usuarioDto)));
    }
    
}
