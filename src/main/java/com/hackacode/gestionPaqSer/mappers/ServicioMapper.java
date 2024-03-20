package com.hackacode.gestionPaqSer.mappers;

import com.hackacode.gestionPaqSer.dtos.ServicioDTO;
import com.hackacode.gestionPaqSer.entities.Servicio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicioMapper {

    public ServicioDTO getServicioDTO(Servicio servicio){
        ServicioDTO servicioDTO = new ServicioDTO();
        servicioDTO.setIdServicio(servicio.getIdServicio());
        servicioDTO.setTipoServicio(servicio.getTipoServicio());
        servicioDTO.setDescripcion(servicio.getDescripcion());
        servicioDTO.setDestiono(servicio.getDestiono());
        servicioDTO.setFechaServicio(servicio.getFechaServicio());
        servicioDTO.setCosto(servicio.getCosto());
        if (!servicio.getImagenes().isEmpty()){
            servicioDTO.setImagenes(
                    servicio.getImagenes().stream().map(i ->{
                        return i.getId();
                    }).collect(Collectors.toList())
            );
        }
        if (servicio.getImagenPrincipal() != null)
            servicioDTO.setImagenPrincipal(String.valueOf(servicio.getImagenPrincipal().getId()));
        return servicioDTO;
    }

    public List<ServicioDTO> getListSErvicioDTO(List<Servicio> servicios){
        return servicios.stream().map(this::getServicioDTO).collect(Collectors.toList());
    }

}
