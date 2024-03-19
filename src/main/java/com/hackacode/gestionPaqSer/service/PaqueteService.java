package com.hackacode.gestionPaqSer.service;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.util.List;
import java.util.Optional;

public interface PaqueteService {
    PaqueteEntity crearPaquete(PaqueteEntity paquete);
    PaqueteEntity obtenerPaquete(String idPaquete) throws MyException;
    List<PaqueteEntity> listarPaquetes();
    PaqueteEntity actualizarPaquete(PaqueteEntity paquete, String paqueteId) throws MyException;
    void eliminarPaquete(String paqueteId) throws MyException;
}
