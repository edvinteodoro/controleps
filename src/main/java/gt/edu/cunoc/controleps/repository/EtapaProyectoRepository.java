/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.EtapasProyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author edvin
 */
public interface EtapaProyectoRepository extends JpaRepository<EtapasProyecto,Integer>{
    @Query("SELECT ep FROM EtapasProyecto ep "
            + "WHERE ep.idEtapaFk.idEtapa = :idEtapa "
            + "AND ep.idProyectoEpsFk.idAnteproyecto = :idProyecto")
    public Optional<EtapasProyecto> getEtapasProyectoPorEtapa(Integer idProyecto, Integer idEtapa);
    
    @Query("SELECT ep FROM EtapasProyecto ep "
            + "WHERE ep.estadoFk.idEstadoEps = :idEstado "
            + "AND ep.idProyectoEpsFk.idAnteproyecto = :idProyecto")
    public Optional<EtapasProyecto> getEtapasProyectoActivo(Integer idProyecto,Integer idEstado);
}
