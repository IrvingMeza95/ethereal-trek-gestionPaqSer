package com.hackacode.gestionPaqSer.controller;

import com.hackacode.gestionPaqSer.dtos.VentaDTO;
import com.hackacode.gestionPaqSer.entity.VentaEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.mappers.VentaMapper;
import com.hackacode.gestionPaqSer.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private VentaMapper ventaMapper;

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaEntity venta) throws MyException {
        return new ResponseEntity<>(ventaMapper.getVentaDTO(ventaService.crearVenta(venta)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerVenta(@PathVariable(value = "id") Integer idVenta) throws MyException {
        return new ResponseEntity<>(ventaMapper.getVentaDTO(ventaService.obtenerVenta(idVenta)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarVentas() {
        return new ResponseEntity<>(ventaMapper.getListVentaDTO(ventaService.listarVentas()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VentaDTO> actualizarVenta(@RequestBody VentaEntity ventaEntity) throws MyException {
        return new ResponseEntity<>(ventaMapper.getVentaDTO(ventaService.actualizarVenta(ventaEntity)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable(value = "id") Integer idVenta) throws MyException {
        ventaService.eliminarVenta(idVenta);
        return new ResponseEntity<>("Venta Eliminada", HttpStatus.NO_CONTENT);
    }

}
