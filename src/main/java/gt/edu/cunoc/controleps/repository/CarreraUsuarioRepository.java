/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.CarrerasUsuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edvin
 */
@Repository
public interface CarreraUsuarioRepository extends JpaRepository<CarrerasUsuario, Integer>{
    @Query("SELECT cu FROM CarrerasUsuario cu WHERE cu.idCarreraFk.idCarrera=:idCarrera "
            + "AND cu.idUsuarioFk.registroAcademico=:registroAcademico")
    public Optional<CarrerasUsuario> getCarreraUsuario(Integer idCarrera, String registroAcademico);
    
    @Query("SELECT cu FROM CarrerasUsuario cu WHERE cu.idUsuarioFk.idRolFk.idRol = :idRol "
            + "ORDER BY ( "
            + "  SELECT COUNT(p) FROM ProyectoEps p WHERE p.idCarrerasSupervisorFk = cu "
            + ") ASC "
            + "LIMIT 1")
    public Optional<CarrerasUsuario> getCarreraUsuarioSupervisor(Integer idRol);
}
