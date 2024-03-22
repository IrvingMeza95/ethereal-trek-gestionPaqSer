package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistroRepository extends JpaRepository<Registro, String> {
    @Query(value = "SELECT r FROM Registro r WHERE r.id = ?1 OR r.venta.idVenta = ?1")
    Registro obetenerPorIdPorIdVenta(String param);
}
