package com.hackacode.gestionPaqSer.services.impl;

import com.hackacode.gestionPaqSer.entities.File;
import com.hackacode.gestionPaqSer.repositories.FileRepository;
import com.hackacode.gestionPaqSer.responses.ResponseFile;
import com.hackacode.gestionPaqSer.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileServiceImp implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File fileEntity = File.builder()
                .name(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        return fileRepository.save(fileEntity);
    }

    @Override
    public File getFile(String id) throws FileNotFoundException {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()){
            return file.get();
        }
        throw new FileNotFoundException();
    }

    @Override
    public List<ResponseFile> getFiles() {
        List<ResponseFile> files = fileRepository.findAll().stream().map(dbFile -> {
            return ResponseFile.builder()
                    .name(dbFile.getName())
                    .url("/api/services/files/" + dbFile.getId())
                    .type(dbFile.getType())
                    .size(dbFile.getData().length)
                    .build();
        }).collect(Collectors.toList());
        return files;
    }

}