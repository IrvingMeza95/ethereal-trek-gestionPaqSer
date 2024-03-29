package com.hackacode.gestionPaqSer.controllers;

import brave.Tracer;
import com.hackacode.gestionPaqSer.dtos.ServicioDTO;
import com.hackacode.gestionPaqSer.entities.Servicio;
import com.hackacode.gestionPaqSer.enums.TipoDeServicio;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.mappers.ServicioMapper;
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
    @Autowired
    private ServicioMapper servicioMapper;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping
    public ResponseEntity<Servicio> crear(@RequestBody Servicio servicio){
        return ResponseEntity.ok(servicioService.crearServicio(servicio));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerServicio(@PathVariable(value = "id") String idServicio) throws MyException {
        return new ResponseEntity<>(servicioMapper.getServicioDTO(servicioService.obtenerServicio(idServicio)),
                HttpStatus.OK);

    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<ServicioDTO>> listarServicios() {
        return new ResponseEntity<>(servicioMapper.getListSErvicioDTO(servicioService.listarServicios())
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizarServicio(@RequestBody Servicio servicio, @PathVariable String id) throws MyException {
        return new ResponseEntity<>(servicioMapper.getServicioDTO(servicioService.actualizarServicio(servicio,id)),
                HttpStatus.OK);
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

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("filtrar/tipo-de-servicio/{tipo}")
    public ResponseEntity<List<ServicioDTO>> filtrarServicioPorTipoDeServicio(@PathVariable String tipo) {
        return new ResponseEntity<>(servicioMapper.getListSErvicioDTO(servicioService.filtrarPorTipoDeServicio(tipo))
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("filtrar/descripcion/{descripcion}")
    public ResponseEntity<List<ServicioDTO>> filtrarServicioPorDescripcion(@PathVariable String descripcion) {
        return new ResponseEntity<>(servicioMapper.getListSErvicioDTO(servicioService.filtrarPorDescripcion(descripcion))
                , HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("filtrar/destino/{destino}")
    public ResponseEntity<List<ServicioDTO>> filtrarServicioPorDestino(@PathVariable String destino) {
        return new ResponseEntity<>(servicioMapper.getListSErvicioDTO(servicioService.filtrarPorDestino(destino))
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
