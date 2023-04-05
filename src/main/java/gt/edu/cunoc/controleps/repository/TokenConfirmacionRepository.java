/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.repository;

import gt.edu.cunoc.controleps.model.entity.TokenConfirmacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author edvin
 */
public interface TokenConfirmacionRepository extends JpaRepository<TokenConfirmacion,Integer>{
    public Optional<TokenConfirmacion> findByToken(String token);
}
