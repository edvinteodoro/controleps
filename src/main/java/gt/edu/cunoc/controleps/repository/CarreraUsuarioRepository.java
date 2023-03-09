/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.CarrerasUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edvin
 */
@Repository
public interface CarreraUsuarioRepository extends JpaRepository<CarrerasUsuario, Integer>{
    
}
