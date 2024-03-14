package com.hackacode.gestionPaqSer.controller;

import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import com.hackacode.gestionPaqSer.service.ServicioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicios")
@Slf4j
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/{id}")
    public ResponseEntity<ServicioEntity> obtenerServicio(@PathVariable(value = "id") Integer idServicio) {
        if (idServicio == null || idServicio <= 0) {
            log.info("El id servicio es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<ServicioEntity> optional = servicioService.obtenerServicio(idServicio);
            if (optional.isPresent()) {
                ServicioEntity servicioEntity = optional.get();
                log.info("Servicio obtenido");
                return new ResponseEntity<>(servicioEntity, HttpStatus.OK);
            } else {
                log.info("Error!!! Servicio no encontrado en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServicioEntity>> listarServicios() {
        List<ServicioEntity> listarServicios = servicioService.listarServicios();
        try {
            log.info("Lista de servicios obtenida");
            return new ResponseEntity<>(listarServicios, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<ServicioEntity> actualizarServicio(@RequestBody ServicioEntity servicioEntity) {
        if (servicioEntity.getIdServicio() == null || servicioEntity.getIdServicio() <= 0) {
            log.info("El id servicio es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<ServicioEntity> optional = servicioService.obtenerServicio(servicioEntity.getIdServicio());
            if (optional.isPresent()) {
                ServicioEntity servicioActualizado = servicioService.actualizarServicio(servicioEntity);
                log.info("Servicio actualizado");
                return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
            } else {
                log.info("Error!!! Servicio a actualizar no existe en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable(value = "id") Integer idServicio) {
        if (idServicio == null || idServicio <= 0) {
            log.info("El id servicio es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<ServicioEntity> servicioOptional = servicioService.obtenerServicio(idServicio);
            if (servicioOptional.isPresent()) {
                ServicioEntity servicioEntity = servicioOptional.get();
                servicioService.eliminarServicio(servicioEntity);
                log.info("Servicio eliminado");
                return new ResponseEntity<>("Servicio Eliminado", HttpStatus.OK);
            } else {
                log.info("Error!!! Servicio a eliminar no existe en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
