package com.hackacode.gestionPaqSer.service;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.util.List;
import java.util.Optional;

public interface PaqueteService {

    public PaqueteEntity crearPaquete(PaqueteEntity paquete);
    public PaqueteEntity obtenerPaquete(Integer idPaquete) throws MyException;
    public List<PaqueteEntity> listarPaquetes();
    public PaqueteEntity actualizarPaquete(PaqueteEntity paquete, Integer paqueteId) throws MyException;
    public void eliminarPaquete(Integer paqueteId) throws MyException;

}
