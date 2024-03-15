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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "destino")
    private String destiono;

    @Column(name = "fecha_servicio")
    private Date fecha_servicio;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "tipo_servicio")
    private String tipoServicio;

}
