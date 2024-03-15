package com.hackacode.gestionPaqSer.repository;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteRepository extends JpaRepository<PaqueteEntity, String> {
    @Query(value = "select s.costo from servicios s where s.id_servicio = ?1", nativeQuery = true)
    String extraerPrecioDeServicio(String idServicio);
}
