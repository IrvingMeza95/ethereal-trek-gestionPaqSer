package com.hackacode.gestionPaqSer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = true)
    private Servicio servicio;
    @ManyToOne
    @JoinColumn(name = "id_paquete", nullable = true)
    private Paquete paquete;
    @Column(name = "tipo_venta", nullable = false)
    private String tipoVenta;
    @Column(name = "fecha_venta", nullable = false)
    private String fechaVenta;
    @Column(name = "medio_pago", nullable = false)
    private String medioPago;
    @Column(nullable = false)
    private Double total;
}
