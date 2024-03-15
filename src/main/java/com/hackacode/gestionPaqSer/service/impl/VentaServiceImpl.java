package com.hackacode.gestionPaqSer.service.impl;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import com.hackacode.gestionPaqSer.entity.VentaEntity;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.repository.VentaRepository;
import com.hackacode.gestionPaqSer.service.PaqueteService;
import com.hackacode.gestionPaqSer.service.ServicioService;
import com.hackacode.gestionPaqSer.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private PaqueteService paqueteService;

    @Override
    public VentaEntity crearVenta(VentaEntity venta) throws MyException {
        setTotal(venta);
        return ventaRepository.save(venta);
    }

    private void setTotal(VentaEntity venta) throws MyException {
        if (venta.getTipoVenta().equals(TipoDeVenta.SERVICIO.name())){
            ServicioEntity servicio = servicioService.obtenerServicio(venta.getServicio().getIdServicio());
            venta.setTotal(servicio.getCosto());
        }else{
            PaqueteEntity paquete = paqueteService.obtenerPaquete(venta.getPaquete().getIdPaquete());
            venta.setTotal(paquete.getPrecio());
        }
    }

    @Override
    public VentaEntity obtenerVenta(Integer idVenta) throws MyException {
        Optional<VentaEntity> ventaEntity = ventaRepository.findById(idVenta);
        if (ventaEntity.isEmpty())
            throw new MyException("Error al cargar datos de la venta.");
        return ventaEntity.get();
    }

    @Override
    public List<VentaEntity> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public VentaEntity actualizarVenta(VentaEntity nuevaVenta, Integer id) throws MyException {
        VentaEntity venta = obtenerVenta(id);
        venta.setCliente(nuevaVenta.getCliente());
        venta.setEmpleado(nuevaVenta.getEmpleado());
        venta.setServicio(nuevaVenta.getServicio());
        venta.setPaquete(nuevaVenta.getPaquete());
        venta.setTipoVenta(nuevaVenta.getTipoVenta());
        venta.setFechaVenta(nuevaVenta.getFechaVenta());
        venta.setMedioPago(nuevaVenta.getMedioPago());
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Integer ventaId) throws MyException {
        VentaEntity ventaEntity = obtenerVenta(ventaId);
        ventaRepository.delete(ventaEntity);
    }

    @Override
    public List<VentaEntity> listarVentasFiltrado(String param) {
        return ventaRepository.listarVentasFiltrado(param);
    }

}
