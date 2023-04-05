package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author edvin
 */
public interface EtapaRepository extends JpaRepository<Etapa,Integer>{
    public Etapa findByIdEtapa(Integer idEtapa);
}
