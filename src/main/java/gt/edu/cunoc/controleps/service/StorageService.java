package gt.edu.cunoc.controleps.service;

import org.springframework.web.multipart.MultipartFile;


public interface StorageService {
    public String saveFile(MultipartFile file) throws Exception;
    public String getFileUrl(Integer idDocumento) throws Exception;
    public String getFileUrl(String url) throws Exception;
}
