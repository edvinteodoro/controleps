/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.CarreraDto;
import gt.edu.cunoc.controleps.model.entity.Carrera;
import gt.edu.cunoc.controleps.repository.CarreraRepository;
import gt.edu.cunoc.controleps.service.CarreraService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edvin
 */
@Service
public class CarreraServiceImp implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;
    
    @Override
    public List<Carrera> findAll() {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera crearCarrera(CarreraDto carreraDto) {
        Carrera carrera=new Carrera();
        carrera.setTitulo(carreraDto.getTitulo()); 
        return carreraRepository.save(carrera);
    }

    @Override
    public Optional<Carrera> getCarreraEstudiante(Integer idCarrera, String usuario) {
        return carreraRepository.getCarreraEstudiante(idCarrera, usuario);
    }
    
}
