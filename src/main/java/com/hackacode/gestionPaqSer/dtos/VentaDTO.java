package com.hackacode.gestionPaqSer.dtos;

import com.hackacode.gestionPaqSer.entity.ClienteEntity;
import com.hackacode.gestionPaqSer.entity.EmpleadoEntity;
import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import com.hackacode.gestionPaqSer.enums.MediosDePago;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {
    private ClienteEntity cliente;
    private EmpleadoEntity empleado;
    private ServicioEntity servicio;
    private PaqueteEntity paquete;
    private TipoDeVenta tipoDeVenta;
    private Date fechaVenta;
    private MediosDePago mediosDePago;
}
