/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edvin
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByCorreo(String correo);

    public Optional<Usuario> findByRegistroAcademico(String registroAcademico);

    public Optional<Usuario> findByNumeroColegiado(String numeroColegiado);

    public List<Usuario> findAll();

    @Query("SELECT u FROM Usuario u "
            + "JOIN u.carrerasUsuarioList cu "
            + "JOIN cu.idCarreraFk c "
            + "JOIN u.idRolFk r "
            + "LEFT JOIN cu.proyectoEpsSupervisorList pe "
            + "WHERE c.idCarrera = :idCarrera AND r.idRol = :idRol "
            + "GROUP BY u.idUsuario "
            + "ORDER BY COUNT(pe.idAnteproyecto) ASC "
            + "LIMIT 1")
    public Optional<Usuario> getSupervisorDisponible(Integer idCarrera, Integer idRol);
    
    @Query("SELECT u FROM Usuario u "
            + "JOIN u.carrerasUsuarioList cu "
            + "JOIN cu.idCarreraFk c "
            + "JOIN u.idRolFk r "
            + "LEFT JOIN cu.proyectoEpsSupervisorList pe "
            + "WHERE c.idCarrera = :idCarrera AND r.idRol = :idRol "
            + "GROUP BY u.idUsuario "
            + "ORDER BY COUNT(pe.idAnteproyecto) ASC "
            + "LIMIT 1")
    public Optional<Usuario> getSecretariaDisponible(Integer idCarrera, Integer idRol);
}
