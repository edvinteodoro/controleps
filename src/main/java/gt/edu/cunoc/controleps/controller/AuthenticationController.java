package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.ActivarUsuarioDto;
import gt.edu.cunoc.controleps.model.dto.AuthResponse;
import gt.edu.cunoc.controleps.model.dto.LoginRequest;
import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import gt.edu.cunoc.controleps.model.entity.Usuario;
import gt.edu.cunoc.controleps.service.UsuarioService;
import gt.edu.cunoc.controleps.service.imp.UsuarioDetailsServiceImp;
import gt.edu.cunoc.controleps.utils.JwtUtil;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edvin
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioDetailsServiceImp userDetailsService;
    private final UsuarioService usuarioService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioDetailsServiceImp userDetailsService,
            JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword(), new ArrayList<>()));
            final UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            String jwt = jwtUtil.generateToken(user);
            AuthResponse authResponse = new AuthResponse(jwt);
            return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/activar")
    public ResponseEntity activarUsuario(@RequestBody ActivarUsuarioDto activarUsuarioDto) {
        try {
            Usuario usuario = usuarioService.activarUsuario(activarUsuarioDto);
            return ResponseEntity.ok(new UsuarioDto(usuario));
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
