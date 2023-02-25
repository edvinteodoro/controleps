/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.Carrera;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edvin
 */
@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    
    @Query("SELECT cu.idCarreraFk FROM CarrerasUsuario cu WHERE cu.idCarreraFk.idCarrera = :idCarrera AND cu.idUsuarioFk.registroAcademico = :registroAcademico")
    public Optional<Carrera> getCarreraEstudiante(Integer idCarrera, String registroAcademico);
    
}
