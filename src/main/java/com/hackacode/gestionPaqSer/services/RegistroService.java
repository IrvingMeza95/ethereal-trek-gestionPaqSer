package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.Registro;
import com.hackacode.gestionPaqSer.entities.Venta;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.util.List;

public interface RegistroService {
    Registro guardar(Venta venta);
    Registro obtenerRegistro(String param) throws MyException;
    List<Registro> listarRegistros();
    Registro actualizar(Venta venta) throws MyException;
    void eliminar(String param) throws MyException;
}
