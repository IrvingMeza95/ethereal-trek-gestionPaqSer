package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, String> {
}
