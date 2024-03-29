package com.hackacode.gestionPaqSer.mappers;

import com.hackacode.gestionPaqSer.dtos.PaqueteDTO;
import com.hackacode.gestionPaqSer.entities.Paquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaqueteMapper {

    @Autowired
    private ServicioMapper servicioMapper;

    public PaqueteDTO getPaqueteDTO(Paquete paquete){
        PaqueteDTO paqueteDTO = new PaqueteDTO();
        paqueteDTO.setIdPaquete(paquete.getIdPaquete());
        if (!paquete.getListaServicios().isEmpty()){
            paqueteDTO.setListaServicios(
               servicioMapper.getListSErvicioDTO(paquete.getListaServicios())
            );
        }
        paqueteDTO.setNombre(paquete.getNombre());
        paqueteDTO.setDescripcion(paquete.getDescripcion());
        paqueteDTO.setPrecio(paquete.getPrecio());
        if (!paquete.getImagenes().isEmpty()){
            paqueteDTO.setImagenes(
                    paquete.getImagenes().stream().map(i -> {
                        return i.getId();
                    }).collect(Collectors.toList())
            );
        }
        if (paquete.getImagenPrincipal() != null)
            paqueteDTO.setImagenPrincipal(paquete.getImagenPrincipal().getId());
        return paqueteDTO;
    }

    public List<PaqueteDTO> getListaPaqueteDTO(List<Paquete> paquetes){
        return paquetes.stream().map(this::getPaqueteDTO).collect(Collectors.toList());
    }

}
