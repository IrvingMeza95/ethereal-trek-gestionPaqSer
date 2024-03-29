package com.hackacode.gestionPaqSer.controllers;

import brave.Tracer;
import com.hackacode.gestionPaqSer.dtos.PaqueteDTO;
import com.hackacode.gestionPaqSer.entities.Paquete;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.mappers.PaqueteMapper;
import com.hackacode.gestionPaqSer.responses.ResponseMessage;
import com.hackacode.gestionPaqSer.services.PaqueteService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/paquetes")
@Slf4j
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;
    @Autowired
    private Tracer tracer;
    @Autowired
    private PaqueteMapper paqueteMapper;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping
    public ResponseEntity<Paquete> crear(@RequestBody Paquete paquete){
        return ResponseEntity.ok(paqueteService.crearPaquete(paquete));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> obtenerPaquete(@PathVariable(value = "id") String idPaquete) throws MyException {
        return new ResponseEntity<>(paqueteService.obtenerPaquete(idPaquete), HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<PaqueteDTO>> listarPaquetes() {
        return new ResponseEntity<>(paqueteMapper.getListaPaqueteDTO(paqueteService.listarPaquetes())
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{id}")
    public ResponseEntity<PaqueteDTO> actualizarPaquete(@RequestBody Paquete paquete,
                                                     @PathVariable String id) throws MyException {
            return new ResponseEntity<>(paqueteMapper.getPaqueteDTO(paqueteService.actualizarPaquete(paquete,id)),
                    HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaquete(@PathVariable(value = "id") String idPaquete) throws MyException {
        paqueteService.eliminarPaquete(idPaquete);
        return new ResponseEntity<>("Paquete Eliminado", HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/actualizar-imagen-principal")
    public void actualizarImagenPrincipal(@RequestParam String paqueteId, @RequestParam String imagenId) throws MyException, FileNotFoundException {
        paqueteService.actualizarImagenPrincipal(paqueteId,imagenId);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/agregar-imagen")
    public void agregarImagen(@RequestParam String paqueteId, @RequestParam String imagenId) throws MyException, FileNotFoundException {
        paqueteService.agregarImagen(paqueteId,imagenId);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/filtrar/nombre/{nomnbre}")
    public ResponseEntity<List<PaqueteDTO>> filtrarPorNombre(@PathVariable String nomnbre) {
        return new ResponseEntity<>(paqueteMapper.getListaPaqueteDTO(paqueteService.filtrarPorNombre(nomnbre))
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/filtrar/descripcion/{descripcion}")
    public ResponseEntity<List<PaqueteDTO>> filtrarPorDescripcion(@PathVariable String descripcion) {
        return new ResponseEntity<>(paqueteMapper.getListaPaqueteDTO(paqueteService.filtrarPorDescripcion(descripcion))
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/filtrar/servicio/{idServicio}")
    public ResponseEntity<List<PaqueteDTO>> filtrarPorServicio(@PathVariable String idServicio) {
        return new ResponseEntity<>(paqueteMapper.getListaPaqueteDTO(paqueteService.filtrarPorServicio(idServicio))
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/filtrar/servicio-tipo-servicio/{tipo}")
    public ResponseEntity<List<PaqueteDTO>> filtrarPorServicioTipoDeServicio(@PathVariable String tipo) {
        return new ResponseEntity<>(paqueteMapper.getListaPaqueteDTO(paqueteService.filtrarPorServicioTipoServicio(tipo))
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/filtrar/servicio-destino/{destino}")
    public ResponseEntity<List<PaqueteDTO>> filtrarPorServicioDestino(@PathVariable String destino) {
        return new ResponseEntity<>(paqueteMapper.getListaPaqueteDTO(paqueteService.filtrarPorServicioDestino(destino))
                , HttpStatus.OK);
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
