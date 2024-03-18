package com.hackacode.gestionPaqSer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servicios")
public class ServicioEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_servicio")
    private String idServicio;
    @Column(name = "tipo_servicio")
    private String tipoServicio;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "destino")
    private String destiono;
    @Column(name = "fecha_servicio")
    private Date fechaServicio;
    @Column(name = "costo")
    private Double costo;
}
