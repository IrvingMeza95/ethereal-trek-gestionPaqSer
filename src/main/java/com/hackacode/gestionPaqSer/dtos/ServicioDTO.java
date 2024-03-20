package com.hackacode.gestionPaqSer.dtos;

import lombok.*;

import java.sql.Date;
import java.util.List;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicioDTO {
    private String idServicio;
    private String tipoServicio;
    private String descripcion;
    private String destiono;
    private Date fechaServicio;
    private Double costo;
    private List<String> imagenes;
    private String imagenPrincipal;
}
