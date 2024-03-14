package com.hackacode.gestionPaqSer.service.impl;

import com.hackacode.gestionPaqSer.entity.VentaEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.repository.VentaRepository;
import com.hackacode.gestionPaqSer.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public VentaEntity crearVenta(VentaEntity venta) throws MyException {
        return ventaRepository.save(venta);
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
    public VentaEntity actualizarVenta(VentaEntity venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Integer ventaId) throws MyException {
        VentaEntity ventaEntity = obtenerVenta(ventaId);
        ventaRepository.delete(ventaEntity);
    }

}
