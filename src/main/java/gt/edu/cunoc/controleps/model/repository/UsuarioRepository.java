/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.model.repository;

import gt.edu.cunoc.controleps.model.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
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
    
    public Optional<Usuario> findByCarnet(String Carnet);

    public List<Usuario> findAll();
}
