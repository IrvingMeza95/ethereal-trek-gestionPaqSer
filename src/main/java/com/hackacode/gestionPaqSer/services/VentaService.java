package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.VentaEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.util.List;

public interface VentaService {
    VentaEntity crearVenta(VentaEntity venta) throws MyException;
    VentaEntity obtenerVenta(Integer idVenta) throws MyException;
    List<VentaEntity> listarVentas();
    VentaEntity actualizarVenta(VentaEntity venta, Integer id) throws MyException;
    void eliminarVenta(Integer ventaId) throws MyException;
    List<VentaEntity> listarVentasFiltrado(String param);
}
