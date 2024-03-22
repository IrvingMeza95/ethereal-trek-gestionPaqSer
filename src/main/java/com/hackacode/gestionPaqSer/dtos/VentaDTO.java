package com.hackacode.gestionPaqSer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {
    private String idVenta;
    private ClienteDTO cliente;
    private EmpleadoDTO empleado;
    private ServicioDTO servicio;
    private PaqueteDTO paquete;
    private String tipoVenta;
    private String fechaVenta;
    private String medioPago;
    private Double total;
}
