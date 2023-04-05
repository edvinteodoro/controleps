package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.Elemento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author edvin
 */
public interface ElementoRepository extends JpaRepository<Elemento,Integer>{
    public Elemento findByIdElemento(Integer idElemento);
}
