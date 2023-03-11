/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.EstadoEps;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author edvin
 */
public interface EstadoEpsRepository extends JpaRepository<EstadoEps, Integer>{
    public EstadoEps findByIdEstadoEps(Integer idEstadoEps);
}
