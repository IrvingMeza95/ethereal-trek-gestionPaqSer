package com.hackacode.gestionPaqSer.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "registros")
public class Registro {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    @OneToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
    @Column(nullable = true)
    private Double porcentajeDescuentoPaquete;
    @Column(nullable = true)
    private Double totalDescuentoPaquete;
    @Column(name = "tipo_venta", nullable = false)
    private String tipoVenta;
    @Column(nullable = false)
    private String fecha;
    @Column(name = "medio_pago", nullable = false)
    private String medioPago;
    @Column(nullable = false)
    private Double porcentajeComisionMedioDePago;
    @Column(nullable = false)
    private Double totalComisionMedioDePago;
    @Column(nullable = false)
    private Double total;
}
