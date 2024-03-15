package com.hackacode.gestionPaqSer.controller;

import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.service.ServicioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping
    public ResponseEntity<ServicioEntity> crear(@RequestBody ServicioEntity servicio){
        return ResponseEntity.ok(servicioService.crearServicio(servicio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioEntity> obtenerServicio(@PathVariable(value = "id") Integer idServicio) throws MyException {
        return new ResponseEntity<>(servicioService.obtenerServicio(idServicio), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<ServicioEntity>> listarServicios() {
        return new ResponseEntity<>(servicioService.listarServicios(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioEntity> actualizarServicio(@RequestBody ServicioEntity servicioEntity, @PathVariable Integer id) throws MyException {
        return new ResponseEntity<>(servicioService.actualizarServicio(servicioEntity,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable(value = "id") Integer idServicio) throws MyException {
        servicioService.eliminarServicio(idServicio);
        return new ResponseEntity<>("Servicio Eliminado", HttpStatus.NO_CONTENT);
    }

}
