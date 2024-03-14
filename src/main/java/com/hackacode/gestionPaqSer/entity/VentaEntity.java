package com.hackacode.gestionPaqSer.entity;

import com.hackacode.gestionPaqSer.enums.MediosDePago;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venta")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    private String clienteId;

    private String empleadoId;

    private Integer ServicioId;

    private Integer paqueteId;

    @Column(name = "tipo_venta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDeVenta tipoVenta;

    @Column(name = "fecha_venta", nullable = false)
    private Date fechaVenta;

    @Column(name = "medio_pago", nullable = false)
    @Enumerated(EnumType.STRING)
    private MediosDePago medioPago;
}
