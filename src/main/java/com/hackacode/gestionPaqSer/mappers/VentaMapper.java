package com.hackacode.gestionPaqSer.mappers;

import com.hackacode.gestionPaqSer.clientes.UsuariosFeign;
import com.hackacode.gestionPaqSer.dtos.VentaDTO;
import com.hackacode.gestionPaqSer.entity.*;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.service.PaqueteService;
import com.hackacode.gestionPaqSer.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VentaMapper {

    @Autowired
    private UsuariosFeign usuariosFeign;
    @Autowired
    private PaqueteService paqueteService;
    @Autowired
    private ServicioService servicioService;

    public VentaDTO getVentaDTO(VentaEntity venta) throws MyException {
        VentaDTO ventaDTO = new VentaDTO();
        Optional<EmpleadoEntity> empleado = Optional.of(usuariosFeign.obtenerEmpleado(venta.getEmpleadoId()));
        if (!empleado.isPresent())
            throw new MyException("Error al cargar los datos del empleado.");

        Optional<ClienteEntity> cliente = Optional.of(usuariosFeign.obtenerCliente(venta.getClienteId()));
        if (!cliente.isPresent())
            throw new MyException("Error al cargar los datos del cliente.");

        if (!venta.getServicioId().equals("") || venta.getServicioId() != null){
            Optional<ServicioEntity> servicio = Optional.of(
                    servicioService.obtenerServicio(venta.getServicioId()).get());
            if (!servicio.isPresent())
                throw new MyException("Error al cargar los datos del servicio.");

            ventaDTO.setTipoDeVenta(TipoDeVenta.SERVICIO);
        }else{
            Optional<PaqueteEntity> paquete = Optional.of(
                    paqueteService.obtenerPaquete(venta.getServicioId()).get());
            if (!paquete.isPresent())
                throw new MyException("Error al cargar los datos del paquete.");

            ventaDTO.setTipoDeVenta(TipoDeVenta.PAQUETE);
        }
        ventaDTO.setTipoDeVenta(venta.getTipoVenta());
        ventaDTO.setFechaVenta(venta.getFechaVenta());
        ventaDTO.setMediosDePago(venta.getMedioPago());
        return ventaDTO;
    }

    public List<VentaDTO> getListVentaDTO(List<VentaEntity> ventaEntities){
        return ventaEntities.stream().map(v -> {
            try {
                return getVentaDTO(v);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

}
