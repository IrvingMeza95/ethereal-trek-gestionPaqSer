package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.Paquete;
import com.hackacode.gestionPaqSer.entities.Servicio;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, String> {
    @Query(value = "select s.costo from servicios s where s.id_servicio = ?1", nativeQuery = true)
    String extraerPrecioDeServicio(String idServicio);
    @Query("SELECT p FROM Paquete p WHERE p.nombre LIKE %?1%")
    List<Paquete> filtrarPorNombre(String nombre);
    @Query("SELECT p FROM Paquete p WHERE p.descripcion LIKE %?1%")
    List<Paquete> filtrarPorDescripcion(String descripcion);
    @Query("SELECT DISTINCT p FROM Paquete p JOIN p.listaServicios s WHERE s.idServicio = ?1")
    List<Paquete> filtrarPorServicio(String idServicio);
    @Query("SELECT DISTINCT p FROM Paquete p JOIN p.listaServicios s WHERE s.tipoServicio = ?1")
    List<Paquete> filtrarPorServicioTipoDeServicio(String tipoServicio);
    @Query("SELECT DISTINCT p FROM Paquete p JOIN p.listaServicios s WHERE s.destiono = ?1")
    List<Paquete> filtrarPorServicioDestino(String destino);
}
