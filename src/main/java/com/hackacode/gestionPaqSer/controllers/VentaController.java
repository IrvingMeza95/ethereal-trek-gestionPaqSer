package com.hackacode.gestionPaqSer.controllers;

import brave.Tracer;
import com.hackacode.gestionPaqSer.dtos.VentaDTO;
import com.hackacode.gestionPaqSer.entities.Venta;
import com.hackacode.gestionPaqSer.enums.MediosDePago;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.mappers.VentaMapper;
import com.hackacode.gestionPaqSer.responses.ResponseMessage;
import com.hackacode.gestionPaqSer.services.VentaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@Slf4j
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private Tracer tracer;
    @Autowired
    private VentaMapper ventaMapper;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) throws MyException {
        return new ResponseEntity<>(ventaService.crearVenta(venta), HttpStatus.CREATED);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerVenta(@PathVariable(value = "id") String idVenta) throws MyException {
        return new ResponseEntity<>(ventaMapper.getVentaDTO(ventaService.obtenerVenta(idVenta)),
                HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarVentas() {
        return new ResponseEntity<>(ventaMapper.getListaVentaDTO(ventaService.listarVentas()),
                HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@RequestBody Venta venta, @PathVariable String id) throws MyException {
        return new ResponseEntity<>(ventaMapper.getVentaDTO(ventaService.actualizarVenta(venta, id)),
                HttpStatus.OK);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable(value = "id") String idVenta) throws MyException {
        ventaService.eliminarVenta(idVenta);
        return new ResponseEntity<>("Venta Eliminada", HttpStatus.NO_CONTENT);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/filtrar/{param}")
    public ResponseEntity<List<VentaDTO>> listarVentasFiltrado(@PathVariable String param){
        return ResponseEntity.ok(ventaMapper.getListaVentaDTO(ventaService.listarVentasFiltrado(param)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/tipos-de-venta")
    public ResponseEntity<List<TipoDeVenta>> tiposDeVenta(){
        return ResponseEntity.ok(List.of(TipoDeVenta.values()));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/medios-de-pago")
    public ResponseEntity<List<MediosDePago>> mediosDePago(){
        return ResponseEntity.ok(List.of(MediosDePago.values()));
    }

    public ResponseEntity<ResponseMessage> metodoAlternativo(Throwable e){
        String error = "Error: " + e.getMessage();
        log.error(error);
        tracer.currentSpan().tag("mensaje.error", error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(error));
    }


}
