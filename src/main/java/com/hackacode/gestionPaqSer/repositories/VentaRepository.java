package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {
    @Query("select v from Venta v where v.cliente.id = ?1 or " +
            "v.empleado.id = ?1 or v.servicio.id = ?1 or v.paquete.id = ?1 or v.tipoVenta = ?1 or v.medioPago = ?1")
    List<Venta> listarVentasFiltrado(String param);
}
