package gt.edu.cunoc.controleps.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    Stream<Path> loadAll();

    List<String> loadAllPaths();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
