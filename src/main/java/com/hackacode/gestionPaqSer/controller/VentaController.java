package com.hackacode.gestionPaqSer.controller;

import com.hackacode.gestionPaqSer.entity.VentaEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
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

    @PostMapping
    public ResponseEntity<VentaEntity> crearVenta(@RequestBody VentaEntity venta) throws MyException {
        return new ResponseEntity<>(ventaService.crearVenta(venta), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaEntity> obtenerVenta(@PathVariable(value = "id") Integer idVenta) throws MyException {
        return new ResponseEntity<>(ventaService.obtenerVenta(idVenta), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VentaEntity>> listarVentas() {
        return new ResponseEntity<>(ventaService.listarVentas(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaEntity> actualizarVenta(@RequestBody VentaEntity ventaEntity, @PathVariable Integer id) throws MyException {
        return new ResponseEntity<>(ventaService.actualizarVenta(ventaEntity, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable(value = "id") Integer idVenta) throws MyException {
        ventaService.eliminarVenta(idVenta);
        return new ResponseEntity<>("Venta Eliminada", HttpStatus.NO_CONTENT);
    }

}
