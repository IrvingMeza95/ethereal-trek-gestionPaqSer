package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.Venta;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.util.List;

public interface VentaService {
    Venta crearVenta(Venta venta) throws MyException;
    Venta obtenerVenta(Integer idVenta) throws MyException;
    List<Venta> listarVentas();
    Venta actualizarVenta(Venta venta, Integer id) throws MyException;
    void eliminarVenta(Integer ventaId) throws MyException;
    List<Venta> listarVentasFiltrado(String param);
}
