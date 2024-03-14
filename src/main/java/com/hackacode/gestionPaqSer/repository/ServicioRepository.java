package com.hackacode.gestionPaqSer.repository;

import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<ServicioEntity, Integer> {
}
