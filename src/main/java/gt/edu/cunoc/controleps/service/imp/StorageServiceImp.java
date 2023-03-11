package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.config.StorageConfig;
import gt.edu.cunoc.controleps.controller.FileStorageController;
import gt.edu.cunoc.controleps.exceptions.StorageException;
import gt.edu.cunoc.controleps.exceptions.StorageFileNotFoundException;
import gt.edu.cunoc.controleps.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageServiceImp implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImp(StorageConfig properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("No fue posible iniciar el almacenamiento", e);
        }
    }

    @Override
    public String store(MultipartFile file){
        try {
            if(file.isEmpty()){
                throw new StorageException("Fallo al guardar el archivo vacio.");
            }

            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename())
            ).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("No es posible almacenar el archivo en la ruta solicitada.");
            }

            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return destinationFile.toAbsolutePath().toString();
        } catch (IOException exception) {
            throw new StorageException("Fallo al almacenar el archivo.", exception);
        }
    }

    @Override
    public List<String> loadAllPaths() {
        List<String> listaArchivos = loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileStorageController.class,
                                        "serveFile", path.getFileName().toString())
                                .build().toUri().toString()
                )
                .collect(Collectors.toList());

        return listaArchivos;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation,1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Fallo al leer los archivos almacenados.", e);
        }
    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }

            throw new StorageFileNotFoundException("No fue posible leer el archivo: " + filename + ".");
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("No fue posible obtener el archivo: " + filename + ".", e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
