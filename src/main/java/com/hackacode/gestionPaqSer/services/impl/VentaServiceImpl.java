package com.hackacode.gestionPaqSer.services.impl;

import com.hackacode.gestionPaqSer.clientes.UsuariosFeign;
import com.hackacode.gestionPaqSer.entities.Paquete;
import com.hackacode.gestionPaqSer.entities.Servicio;
import com.hackacode.gestionPaqSer.entities.Venta;
import com.hackacode.gestionPaqSer.enums.MediosDePago;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.repositories.VentaRepository;
import com.hackacode.gestionPaqSer.services.PaqueteService;
import com.hackacode.gestionPaqSer.services.ServicioService;
import com.hackacode.gestionPaqSer.services.VentaService;
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
    @Autowired
    private UsuariosFeign usuariosFeign;

    @Override
    public Venta crearVenta(Venta venta) throws MyException {
        setTotal(venta);
        venta.setCliente(usuariosFeign.obtenerCliente(venta.getCliente().getId()));
        venta.setEmpleado(usuariosFeign.obtenerEmpleado(venta.getEmpleado().getId()));
        Venta ventaEntity = ventaRepository.save(venta);

        return ventaEntity;
    }

    private void setTotal(Venta venta) throws MyException {
        if (venta.getTipoVenta().equals(TipoDeVenta.SERVICIO.name())){
            Servicio servicio = servicioService.obtenerServicio(venta.getServicio().getIdServicio());
            venta.setTotal(servicio.getCosto());
            venta.setServicio(servicioService.obtenerServicio(venta.getServicio().getIdServicio()));
        }else{
            Paquete paquete = paqueteService.obtenerPaquete(venta.getPaquete().getIdPaquete());
            venta.setTotal(paquete.getPrecio());
            venta.setPaquete(paqueteService.obtenerPaquete(venta.getPaquete().getIdPaquete()));
        }
        for (MediosDePago mp : MediosDePago.values()){
            if (mp.name().equals(venta.getMedioPago())){
                venta.setTotal(Math.round((venta.getTotal() * (1 + mp.getComision())) * 100.0) / 100.0);
                break;
            }
        }
    }

    @Override
    public Venta obtenerVenta(Integer idVenta) throws MyException {
        Optional<Venta> ventaEntity = ventaRepository.findById(idVenta);
        if (ventaEntity.isEmpty())
            throw new MyException("Error al cargar datos de la venta.");
        return ventaEntity.get();
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta actualizarVenta(Venta nuevaVenta, Integer id) throws MyException {
        Venta venta = obtenerVenta(id);
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
        Venta venta = obtenerVenta(ventaId);
        ventaRepository.delete(venta);
    }

    @Override
    public List<Venta> listarVentasFiltrado(String param) {
        return ventaRepository.listarVentasFiltrado(param);
    }

}
