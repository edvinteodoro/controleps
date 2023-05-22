/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edvin
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Integer>{
    @Query("SELECT c FROM EtapasProyecto ep "
        + "JOIN ep.comentarioList c "
        + "WHERE ep.idProyectoEpsFk.idAnteproyecto = :idProyecto "
        + "AND ep.idEtapaFk.idEtapa = :idEtapa "
        + "ORDER BY c.idComentario DESC")
    public List<Comentario> getComentariosEtapa(Integer idProyecto, Integer idEtapa);
}
