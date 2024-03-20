package com.hackacode.gestionPaqSer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ventas")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private EmpleadoEntity empleado;

    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = true)
    private ServicioEntity servicio;

    @ManyToOne
    @JoinColumn(name = "id_paquete", nullable = true)
    private PaqueteEntity paquete;

    @Column(name = "tipo_venta", nullable = false)
    private String tipoVenta;

    @Column(name = "fecha_venta", nullable = false)
    private Date fechaVenta;

    @Column(name = "medio_pago", nullable = false)
    private String medioPago;

    @Column(nullable = false)
    private Double total;

}
