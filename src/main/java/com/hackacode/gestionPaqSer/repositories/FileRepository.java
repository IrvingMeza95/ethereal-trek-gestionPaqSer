package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String> {
}
