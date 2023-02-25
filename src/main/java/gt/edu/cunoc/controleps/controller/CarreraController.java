/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.CarreraDto;
import gt.edu.cunoc.controleps.service.CarreraService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edvin
 */
@RestController
@RequestMapping("/api/carreras")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;
    
    @GetMapping
    public ResponseEntity<List<CarreraDto>> getCarreras() {
        List<CarreraDto> carreraList = carreraService.findAll().stream()
        .map(carrera -> new CarreraDto(carrera))
        .collect(Collectors.toList());
        return ResponseEntity.ok(carreraList);
    }
    
    @PostMapping
    public ResponseEntity<CarreraDto> crearCarrera(@RequestBody CarreraDto carreraDto) {
        System.out.println("carrera post");
        return ResponseEntity.ok(carreraDto);
    }
}
