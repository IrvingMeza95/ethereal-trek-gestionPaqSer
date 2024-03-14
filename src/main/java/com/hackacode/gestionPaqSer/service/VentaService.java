package com.hackacode.gestionPaqSer.service;

import com.hackacode.gestionPaqSer.entity.VentaEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.util.List;

public interface VentaService {
    VentaEntity crearVenta(VentaEntity venta) throws MyException;
    VentaEntity obtenerVenta(Integer idVenta) throws MyException;
    List<VentaEntity> listarVentas();
    VentaEntity actualizarVenta(VentaEntity venta);
    void eliminarVenta(Integer ventaId) throws MyException;
}
