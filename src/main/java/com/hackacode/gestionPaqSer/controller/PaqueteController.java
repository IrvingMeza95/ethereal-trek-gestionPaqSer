package com.hackacode.gestionPaqSer.controller;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.service.PaqueteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paquetes")
@Slf4j
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @GetMapping("/{id}")
    public ResponseEntity<PaqueteEntity> obtenerPaquete(@PathVariable(value = "id") Integer idPaquete) {
        if (idPaquete == null || idPaquete <= 0) {
            log.info("El id paquete es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<PaqueteEntity> optional = paqueteService.obtenerPaquete(idPaquete);
            if (optional.isPresent()) {
                PaqueteEntity paqueteEntity = optional.get();
                log.info("Paquete obtenido");
                return new ResponseEntity<>(paqueteEntity, HttpStatus.OK);
            } else {
                log.info("Error!!! Paquete no encontrado en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<PaqueteEntity>> listarPaquetes() {
        List<PaqueteEntity> listarPaquetes = paqueteService.listarPaquetes();
        try {
            log.info("Lista de paquetes obtenida");
            return new ResponseEntity<>(listarPaquetes, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<PaqueteEntity> actualizarPaquete(@RequestBody PaqueteEntity paqueteEntity) {
        if (paqueteEntity.getIdPaquete() == null || paqueteEntity.getIdPaquete() <= 0) {
            log.info("El id paquete es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<PaqueteEntity> optional = paqueteService.obtenerPaquete(paqueteEntity.getIdPaquete());
            if (optional.isPresent()) {
                PaqueteEntity paqueteActualizado = paqueteService.actualizarPaquete(paqueteEntity);
                log.info("Paquete actualizado");
                return new ResponseEntity<>(paqueteActualizado, HttpStatus.OK);
            } else {
                log.info("Error!!! Paquete a actualizar no existe en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaquete(@PathVariable(value = "id") Integer idPaquete) {
        if (idPaquete == null || idPaquete <= 0) {
            log.info("El id paquete es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<PaqueteEntity> paqueteOptional = paqueteService.obtenerPaquete(idPaquete);
            if (paqueteOptional.isPresent()) {
                PaqueteEntity paqueteEntity = paqueteOptional.get();
                paqueteService.eliminarPaquete(paqueteEntity);
                log.info("Paquete eliminado");
                return new ResponseEntity<>("Paquete Eliminado", HttpStatus.OK);
            } else {
                log.info("Error!!! Paquete a eliminar no existe en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
