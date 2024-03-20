package com.hackacode.gestionPaqSer.controllers;

import brave.Tracer;
import com.hackacode.gestionPaqSer.entities.ServicioEntity;
import com.hackacode.gestionPaqSer.enums.TipoDeServicio;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.responses.ResponseMessage;
import com.hackacode.gestionPaqSer.services.ServicioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/servicios")
@Slf4j
public class ServicioController {

    @Autowired
    private ServicioService servicioService;
    @Autowired
    private Tracer tracer;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping
    public ResponseEntity<ServicioEntity> crear(@RequestBody ServicioEntity servicio){
        return ResponseEntity.ok(servicioService.crearServicio(servicio));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}")
    public ResponseEntity<ServicioEntity> obtenerServicio(@PathVariable(value = "id") String idServicio) throws MyException {
        return new ResponseEntity<>(servicioService.obtenerServicio(idServicio), HttpStatus.OK);

    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<ServicioEntity>> listarServicios() {
        return new ResponseEntity<>(servicioService.listarServicios(), HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{id}")
    public ResponseEntity<ServicioEntity> actualizarServicio(@RequestBody ServicioEntity servicioEntity, @PathVariable String id) throws MyException {
        return new ResponseEntity<>(servicioService.actualizarServicio(servicioEntity,id), HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable(value = "id") String idServicio) throws MyException {
        servicioService.eliminarServicio(idServicio);
        return new ResponseEntity<>("Servicio Eliminado", HttpStatus.NO_CONTENT);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/tipo-de-servicios")
    public ResponseEntity<List<TipoDeServicio>> tiposDeServicio(){
        return ResponseEntity.ok(List.of(TipoDeServicio.values()));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/actualizar-imagen-principal")
    public void actualizarImagenPrincipal(@RequestParam String servicioId, @RequestParam String imagenId) throws MyException, FileNotFoundException {
        servicioService.actualizarImagenPrincipal(servicioId,imagenId);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/agregar-imagen")
    public void agregarImagen(@RequestParam String servicioId, @RequestParam String imagenId) throws MyException, FileNotFoundException {
        servicioService.agregarImagen(servicioId,imagenId);
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
