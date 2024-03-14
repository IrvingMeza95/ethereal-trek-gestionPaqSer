package com.hackacode.gestionPaqSer.repository;

import com.hackacode.gestionPaqSer.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Integer> {
}
