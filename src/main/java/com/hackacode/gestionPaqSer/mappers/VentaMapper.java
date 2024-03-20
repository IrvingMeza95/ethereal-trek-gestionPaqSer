package com.hackacode.gestionPaqSer.mappers;

import com.hackacode.gestionPaqSer.dtos.VentaDTO;
import com.hackacode.gestionPaqSer.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VentaMapper {

    @Autowired
    private ServicioMapper servicioMapper;
    @Autowired
    private PaqueteMapper paqueteMapper;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private EmpleadoMapper empleadoMapper;

    public VentaDTO getVentaDTO(Venta venta){
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setIdVenta(venta.getIdVenta());
        if (venta.getCliente() != null)
            ventaDTO.setCliente(clienteMapper.getClienteDTO(venta.getCliente()));
        if (venta.getEmpleado() != null)
            ventaDTO.setEmpleado(empleadoMapper.getEmpleadoDTO(venta.getEmpleado()));
        if (venta.getServicio() != null)
            ventaDTO.setServicio(servicioMapper.getServicioDTO(venta.getServicio()));
        if (venta.getPaquete() != null)
            ventaDTO.setPaquete(paqueteMapper.getPaqueteDTO(venta.getPaquete()));
        ventaDTO.setTipoVenta(venta.getTipoVenta());
        ventaDTO.setFechaVenta(venta.getFechaVenta());
        ventaDTO.setMedioPago(venta.getMedioPago());
        ventaDTO.setTotal(venta.getTotal());
        return ventaDTO;
    }

    public List<VentaDTO> getListaVentaDTO(List<Venta> ventas){
        return ventas.stream().map(this::getVentaDTO).collect(Collectors.toList());
    }

}
