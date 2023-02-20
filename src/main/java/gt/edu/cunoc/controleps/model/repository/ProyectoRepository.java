/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.model.repository;

import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author edvin
 */
public interface ProyectoRepository extends JpaRepository<ProyectoEps, Integer>{
    
}
