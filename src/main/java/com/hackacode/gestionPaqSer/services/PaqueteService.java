package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.Paquete;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.io.FileNotFoundException;
import java.util.List;

public interface PaqueteService {
    Paquete crearPaquete(Paquete paquete);
    Paquete obtenerPaquete(String idPaquete) throws MyException;
    List<Paquete> listarPaquetes();
    Paquete actualizarPaquete(Paquete paquete, String paqueteId) throws MyException;
    void eliminarPaquete(String paqueteId) throws MyException;
    void actualizarImagenPrincipal(String paqueteId, String imagenId) throws MyException, FileNotFoundException;
    void agregarImagen(String paqueteId, String imagenId) throws MyException, FileNotFoundException;
}
