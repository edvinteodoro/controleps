/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.service;

import gt.edu.cunoc.controleps.model.dto.RolDto;
import gt.edu.cunoc.controleps.model.entity.Rol;
import java.util.List;

/**
 *
 * @author edvin
 */


public interface RolService {
    public Rol getRolByIdRol(Integer Id);
    public List<Rol> getAll();
    public Rol crearRol(RolDto rolDto);
    public Rol actualizarRol(RolDto rolDto);
}
