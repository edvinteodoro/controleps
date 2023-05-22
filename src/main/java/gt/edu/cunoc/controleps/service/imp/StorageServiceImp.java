package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.config.StorageConfig;
import gt.edu.cunoc.controleps.exceptions.StorageException;
import gt.edu.cunoc.controleps.model.entity.ElementosProyecto;
import gt.edu.cunoc.controleps.repository.ElementoProyectoRepository;
import gt.edu.cunoc.controleps.service.StorageService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Service
public class StorageServiceImp implements StorageService {

    private final MinioClient minioClient;
    private final String bucketName;
    private final ElementoProyectoRepository elementoProyectoRepository;
    
    @Autowired
    public StorageServiceImp(StorageConfig properties,MinioClient minioClient, ElementoProyectoRepository elementoProyectoRepository,
            @Value("${minio.bucketName}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
        this.elementoProyectoRepository = elementoProyectoRepository;
    }

    @Override
    public String getFileUrl(Integer idDocumento) throws Exception {
        ElementosProyecto elemento= this.elementoProyectoRepository.findByIdElementosProyecto(idDocumento);
        String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(elemento.getInformacion())
                .expiry(60)
                .build()); 
        return url;
    }

    @Override
    public String saveFile(MultipartFile file) throws Exception {
        //System.out.println("save");
        if (!file.isEmpty()) {
            try (InputStream inputStream = file.getInputStream()) {
                String fileName = file.getOriginalFilename();
                String objectName = UUID.randomUUID().toString() + "/" + fileName;
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());

                return objectName;
            }
        } else {
            throw new StorageException("Fallo al guardar el archivo vacio.");
        }
    }

    @Override
    public String getFileUrl(String url) throws Exception {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(url)
                .expiry(60)
                .build());
    }
}
