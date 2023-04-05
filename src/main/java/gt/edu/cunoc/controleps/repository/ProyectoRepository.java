/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.ProyectoEps;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author edvin
 */
public interface ProyectoRepository extends JpaRepository<ProyectoEps, Integer> {

    @Query("SELECT p FROM ProyectoEps p "
            + "WHERE p.idEstadoEpsFk.idEstadoEps = :idEstadoEps "
            + "AND p.idCarrerasUsuarioFk.idUsuarioFk.idUsuario = :idUsuario")
    public Optional<ProyectoEps> getProyectoActivo(Integer idEstadoEps, Integer idUsuario);
    
    @Query("SELECT p FROM ProyectoEps p "
            + "WHERE p.idCarrerasUsuarioFk.idUsuarioFk.registroAcademico = :registroAcademico")
    public List<ProyectoEps> getProyectosUsuario(String registroAcademico);
    
    @Query("SELECT p FROM ProyectoEps p "
            + "WHERE p.idSecretariaFk.registroAcademico = :registroAcademico")
    public List<ProyectoEps> getProyectosSecretaria(String registroAcademico);

    public Optional<ProyectoEps> findByIdAnteproyecto(Integer idAnteproyecto);
}
