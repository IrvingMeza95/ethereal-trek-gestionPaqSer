package com.hackacode.gestionPaqSer.controllers;

import brave.Tracer;
import com.hackacode.gestionPaqSer.entities.File;
import com.hackacode.gestionPaqSer.responses.ResponseFile;
import com.hackacode.gestionPaqSer.responses.ResponseMessage;
import com.hackacode.gestionPaqSer.services.FileService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private Tracer tracer;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping
    public ResponseEntity<ResponseFile> subir(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType() == null){
            throw new IOException();
        }
        File newFile = fileService.store(file);
        return ResponseEntity.status(HttpStatus.OK).body(fileService.mapearFile(newFile));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> verArchivo(@PathVariable String id) throws FileNotFoundException {
        File file = fileService.getFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, file.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/files-paths")
    public ResponseEntity<List<ResponseFile>> listarArchivos(){
        List<ResponseFile> files = fileService.getFiles();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    public ResponseEntity<ResponseMessage> metodoAlternativo(Throwable e){
        String error = "";
        if (e.getMessage() != null){
            error  = "Error: " + e.getMessage();
        } else if (e.fillInStackTrace() != null) {
            error = "Error: " + e.fillInStackTrace();
        }else {
            error = "Error: Algo salió mal.";
        }

        log.error(error);
        tracer.currentSpan().tag("mensaje.error", error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(error));
    }

}
