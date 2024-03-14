package com.hackacode.gestionPaqSer.repository;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteRepository extends JpaRepository<PaqueteEntity, Integer> {
}
