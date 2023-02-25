/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.service;

import gt.edu.cunoc.controleps.model.dto.CarreraDto;
import gt.edu.cunoc.controleps.model.entity.Carrera;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author edvin
 */
public interface CarreraService {
    public List<Carrera> findAll();
    public Carrera crearCarrera(CarreraDto carreraDto);
    public Optional<Carrera> getCarreraEstudiante(Integer idCarrera, String usuario);
}
