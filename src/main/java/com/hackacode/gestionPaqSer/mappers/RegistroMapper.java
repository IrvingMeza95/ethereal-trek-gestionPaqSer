package com.hackacode.gestionPaqSer.mappers;

import com.hackacode.gestionPaqSer.dtos.RegistroDTO;
import com.hackacode.gestionPaqSer.entities.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegistroMapper {

    @Autowired
    private VentaMapper ventaMapper;

    public RegistroDTO getRegistroDTO(Registro registro){
        RegistroDTO registroDTO = RegistroDTO.builder()
        		.id(registro.getId())
        		.venta(ventaMapper.getVentaDTO(registro.getVenta()))
        		.porcentajeDescuentoPaquete(registro.getPorcentajeDescuentoPaquete())
        		.totalDescuentoPaquete(registro.getTotalDescuentoPaquete())
        		.tipoVenta(registro.getTipoVenta())
        		.fecha(registro.getFecha())
        		.medioPago(registro.getMedioPago())
        		.porcentajeComisionMedioDePago(registro.getPorcentajeComisionMedioDePago())
        		.totalComisionMedioDePago(registro.getTotalComisionMedioDePago())
        		.total(registro.getTotal())
        		.build();
        return registroDTO;
    }

    public List<RegistroDTO> getListaRegistroDTO(List<Registro> registros){
        return registros.stream().map(this::getRegistroDTO).collect(Collectors.toList());
    }

}
