package com.hackacode.gestionPaqSer.entity;

import com.hackacode.gestionPaqSer.enums.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoEntity extends PersonaEntity {
    private Cargo cargo;
    private Double sueldo;
}
