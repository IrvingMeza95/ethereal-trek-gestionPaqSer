package com.hackacode.gestionPaqSer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PersonaDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNac;
    private String pais;
    private String celular;
    private String email;
    private String direccion;
    private Boolean enabled;
}
