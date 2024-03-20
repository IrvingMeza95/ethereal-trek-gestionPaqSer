package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.PaqueteEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.io.FileNotFoundException;
import java.util.List;

public interface PaqueteService {
    PaqueteEntity crearPaquete(PaqueteEntity paquete);
    PaqueteEntity obtenerPaquete(String idPaquete) throws MyException;
    List<PaqueteEntity> listarPaquetes();
    PaqueteEntity actualizarPaquete(PaqueteEntity paquete, String paqueteId) throws MyException;
    void eliminarPaquete(String paqueteId) throws MyException;
    void actualizarImagenPrincipal(String paqueteId, String imagenId) throws MyException, FileNotFoundException;
    void agregarImagen(String paqueteId, String imagenId) throws MyException, FileNotFoundException;
}
