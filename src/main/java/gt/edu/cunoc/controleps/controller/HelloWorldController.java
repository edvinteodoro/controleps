/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import java.nio.file.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edvin
 */
@RestController
@RequestMapping("/api/hello-world")
public class HelloWorldController {

    //@PreAuthorize(value="hasRole('ROLE_Estudiante')")
    @PreAuthorize("hasRole('ROLE_Secretaria')")
    @GetMapping
    public String helloWorld() throws AccessDeniedException {
        return "hello world";
    }

    //@PreAuthorize(value="hasRole('ROLE_Supervisor')")
    @PreAuthorize("hasRole('ROLE_Estudiante')")
    @GetMapping("/2")
    public String helloWorld2() {
        return "hello world 2";
    }

}
