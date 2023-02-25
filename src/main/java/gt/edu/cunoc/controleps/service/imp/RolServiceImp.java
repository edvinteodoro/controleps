/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.dto.RolDto;
import gt.edu.cunoc.controleps.model.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gt.edu.cunoc.controleps.repository.RolRepository;
import gt.edu.cunoc.controleps.service.RolService;
import java.util.List;

/**
 *
 * @author edvin
 */
@Service
public class RolServiceImp implements RolService{

    @Autowired
    private RolRepository rolRepository;
    
    @Override
    public Rol getRolByIdRol(Integer idRol) {
        return rolRepository.findByIdRol(idRol);
    }

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol crearRol(RolDto rolDto) {
        Rol rol=new Rol(rolDto);
        return rolRepository.save(rol);
    }

    @Override
    public Rol actualizarRol(RolDto rolDto) {
        Rol rol=new Rol(rolDto);
        rol.setIdRol(rolDto.getIdRol());
        return rolRepository.save(rol); 
    }
    
}
