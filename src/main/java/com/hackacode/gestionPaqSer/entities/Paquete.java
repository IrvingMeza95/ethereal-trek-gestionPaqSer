package com.hackacode.gestionPaqSer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paquetes")
public class Paquete {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_paquete")
    private String idPaquete;
    @ManyToMany
    @JoinTable(
            name = "detalle_paquete_servicio",
            joinColumns = @JoinColumn(name = "id_paquete"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio"))
    private List<Servicio> listaServicios;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @ManyToMany
    @JoinTable(name = "paquetes_imagenes",
            joinColumns = @JoinColumn(name = "id_paquete"),
            inverseJoinColumns = @JoinColumn(name = "id_imagen"))
    private List<File> imagenes;
    @OneToOne
    @JoinColumn(name = "id_imagen_principal")
    private File imagenPrincipal;
}
