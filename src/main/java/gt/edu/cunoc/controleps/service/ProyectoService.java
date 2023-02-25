/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.service;

import gt.edu.cunoc.controleps.model.dto.ProyectoDto;
import gt.edu.cunoc.controleps.model.entity.ProyectoEps;

/**
 *
 * @author edvin
 */
public interface ProyectoService {

    public ProyectoEps crearProyectoEps(ProyectoDto proyectoDto, String usuario);

}
