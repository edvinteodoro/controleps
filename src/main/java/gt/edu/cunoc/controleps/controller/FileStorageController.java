package gt.edu.cunoc.controleps.controller;

import gt.edu.cunoc.controleps.exceptions.StorageFileNotFoundException;
import gt.edu.cunoc.controleps.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/storage")
public class FileStorageController {

    private final StorageService storageService;

    @Autowired
    public FileStorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<String>> listUploadedFiles() throws IOException {
        List<String> listaArchivos = storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileStorageController.class,
                                        "serveFile", path.getFileName().toString())
                                .build().toUri().toString()
                )
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaArchivos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        String filePath = storageService.store(file);

        return ResponseEntity.ok("{\"info\": \"archivo (" + file.getOriginalFilename() + ") guardado correctamente. ("+ filePath +")\"}");
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
