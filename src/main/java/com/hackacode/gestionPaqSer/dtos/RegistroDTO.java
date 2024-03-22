package com.hackacode.gestionPaqSer.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistroDTO {
    private String id;
    private VentaDTO venta;
    private Double porcentajeDescuentoPaquete;
    private Double totalDescuentoPaquete;
    private String tipoVenta;
    private String fecha;
    private String medioPago;
    private Double porcentajeComisionMedioDePago;
    private Double totalComisionMedioDePago;
    private Double total;
}
