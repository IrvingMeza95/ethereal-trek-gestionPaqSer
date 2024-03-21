package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, String> {
    @Query("SELECT s FROM Servicio s WHERE s.tipoServicio = ?1")
    List<Servicio> filtrarPorTipoDeServicio(String tipo);
    @Query("SELECT s FROM Servicio s WHERE s.descripcion LIKE %?1%")
    List<Servicio> filtrarPorDescripcion(String descripcion);
    @Query("SELECT s FROM Servicio s WHERE s.destiono = ?1")
    List<Servicio> filtrarPorDestino(String destino);
}
