package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, String> {
    @Query(value = "select s.costo from servicios s where s.id_servicio = ?1", nativeQuery = true)
    String extraerPrecioDeServicio(String idServicio);
}
