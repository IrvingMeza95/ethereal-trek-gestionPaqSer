package com.hackacode.gestionPaqSer.controllers;

import brave.Tracer;
import com.hackacode.gestionPaqSer.dtos.RegistroDTO;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.mappers.RegistroMapper;
import com.hackacode.gestionPaqSer.responses.ResponseMessage;
import com.hackacode.gestionPaqSer.services.RegistroService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
@Slf4j
public class RegistroController {

    @Autowired
    private RegistroService registroService;
    @Autowired
    private RegistroMapper registroMapper;
    @Autowired
    private Tracer tracer;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{param}")
    public ResponseEntity<RegistroDTO> busucar(@PathVariable String param) throws MyException {
        return ResponseEntity.ok(registroMapper.getRegistroDTO(registroService.obtenerRegistro(param)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<RegistroDTO>> listarRegistros() throws MyException {
        return ResponseEntity.ok(registroMapper.getListaRegistroDTO(registroService.listarRegistros()));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("resumen-medio-pago/anio/{anio}/mes/{mes}")
    public ResponseEntity<List<Object[]>> resumenPorAnioMesMedioDePago(@PathVariable String anio, @PathVariable String mes){
        return ResponseEntity.ok(registroService.resumenPorAnioMesMedioDePago(anio,mes));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("resumen-tipo-venta/anio/{anio}/mes/{mes}")
    public ResponseEntity<List<Object[]>> resumenPorAnioMesTipoDeVenta(@PathVariable String anio, @PathVariable String mes){
        return ResponseEntity.ok(registroService.resumenPorAnioMesTipoDeVenta(anio,mes));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("resumen-servicios-tipo-servicio/anio/{anio}/mes/{mes}")
    public ResponseEntity<List<Object[]>> resumenDeServiciosPorAnioMesTipoDeServicios(@PathVariable String anio, @PathVariable String mes){
        return ResponseEntity.ok(registroService.resumenDeServiciosPorAnioMesTipoDeServicio(anio,mes));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("resumen-paquetes-tipo-servicio/anio/{anio}/mes/{mes}")
    public ResponseEntity<List<Object[]>> resumenDePaquetesPorAnioMesTipoDeServicios(@PathVariable String anio, @PathVariable String mes){
        return ResponseEntity.ok(registroService.resumenDePaquetesPorAnioMesTipoDeServicio(anio,mes));
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
