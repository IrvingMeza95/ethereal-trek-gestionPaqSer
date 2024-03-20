package com.hackacode.gestionPaqSer.dtos;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaqueteDTO {
    private String idPaquete;
    private List<String> listaServicios;
    private String nombre;
    private String descripcion;
    private Double precio;
    private List<String> imagenes;
    private String imagenPrincipal;
}
