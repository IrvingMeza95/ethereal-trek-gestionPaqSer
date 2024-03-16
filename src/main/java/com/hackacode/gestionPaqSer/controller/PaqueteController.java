package com.hackacode.gestionPaqSer.controller;

import brave.Tracer;
import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.responses.ResponseMessage;
import com.hackacode.gestionPaqSer.service.PaqueteService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paquetes")
@Slf4j
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;
    @Autowired
    private Tracer tracer;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping
    public ResponseEntity<PaqueteEntity> crear(@RequestBody PaqueteEntity paquete){
        return ResponseEntity.ok(paqueteService.crearPaquete(paquete));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaqueteEntity> obtenerPaquete(@PathVariable(value = "id") String idPaquete) throws MyException {
        return new ResponseEntity<>(paqueteService.obtenerPaquete(idPaquete), HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<PaqueteEntity>> listarPaquetes() {
        try {
            return new ResponseEntity<>(paqueteService.listarPaquetes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{id}")
    public ResponseEntity<PaqueteEntity> actualizarPaquete(@RequestBody PaqueteEntity paqueteEntity,
                                                           @PathVariable String id) throws MyException {
            return new ResponseEntity<>(paqueteService.actualizarPaquete(paqueteEntity,id), HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaquete(@PathVariable(value = "id") String idPaquete) throws MyException {
        paqueteService.eliminarPaquete(idPaquete);
        return new ResponseEntity<>("Paquete Eliminado", HttpStatus.OK);
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
