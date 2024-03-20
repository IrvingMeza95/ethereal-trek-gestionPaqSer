package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.File;
import com.hackacode.gestionPaqSer.responses.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {
    File store(MultipartFile file) throws IOException;
    File getFile(String id) throws FileNotFoundException;
    List<ResponseFile> getFiles();
}
