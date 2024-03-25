package com.hackacode.gestionPaqSer.clientes;

import com.hackacode.gestionPaqSer.entities.Venta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gestionEmail")
public interface GestionEmailFeign {
    @PostMapping("/verificacion")
    String generarEmail(@RequestBody Venta venta);
}
