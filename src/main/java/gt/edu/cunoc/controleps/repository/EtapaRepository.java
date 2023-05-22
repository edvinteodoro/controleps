package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.Etapa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author edvin
 */
public interface EtapaRepository extends JpaRepository<Etapa,Integer>{
    public Etapa findByIdEtapa(Integer idEtapa);
    
    @Query("SELECT ep.idEtapaFk FROM EtapasProyecto ep "
            + "WHERE ep.idProyectoEpsFk.idAnteproyecto = :idProyecto")
    public List<Etapa> getEtapasProyecto(Integer idProyecto);
}
