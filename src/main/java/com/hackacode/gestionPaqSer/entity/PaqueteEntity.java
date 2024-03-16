package com.hackacode.gestionPaqSer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paquetes")
public class PaqueteEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_paquete")
    private String idPaquete;

    @ManyToMany
    @JoinTable(
            name = "detalle_paquete_servicio",
            joinColumns = @JoinColumn(name = "id_paquete"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio"))
    private Set<ServicioEntity> listaServicios = new HashSet<>();

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

}
