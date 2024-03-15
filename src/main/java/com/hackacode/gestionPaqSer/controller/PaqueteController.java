package com.hackacode.gestionPaqSer.controller;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paquetes")
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @PostMapping
    public ResponseEntity<PaqueteEntity> crear(@RequestBody PaqueteEntity paquete){
        return ResponseEntity.ok(paqueteService.crearPaquete(paquete));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaqueteEntity> obtenerPaquete(@PathVariable(value = "id") String idPaquete) throws MyException {
        return new ResponseEntity<>(paqueteService.obtenerPaquete(idPaquete), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PaqueteEntity>> listarPaquetes() {
        try {
            return new ResponseEntity<>(paqueteService.listarPaquetes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaqueteEntity> actualizarPaquete(@RequestBody PaqueteEntity paqueteEntity, @PathVariable String id) throws MyException {
            return new ResponseEntity<>(paqueteService.actualizarPaquete(paqueteEntity,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaquete(@PathVariable(value = "id") String idPaquete) throws MyException {
        paqueteService.eliminarPaquete(idPaquete);
        return new ResponseEntity<>("Paquete Eliminado", HttpStatus.OK);
    }

}
