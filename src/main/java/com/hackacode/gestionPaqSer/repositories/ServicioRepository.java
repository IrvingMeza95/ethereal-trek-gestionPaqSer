package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.ServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<ServicioEntity, String> {
}
