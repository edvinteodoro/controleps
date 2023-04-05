/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.model.dto.DocumentoDto;
import gt.edu.cunoc.controleps.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edvin
 */
@RestController
@RequestMapping("/api/documentos")
public class DocumentosController {

    private final StorageService storageService;

    public DocumentosController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{idElemento}")
    public ResponseEntity getDocumentoUrl(@PathVariable(value = "idElemento") Integer idElemento) throws Exception {
        try {
            return ResponseEntity.ok(new DocumentoDto(storageService.getFileUrl(idElemento)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
