package com.hackacode.gestionPaqSer.service;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;

import java.util.List;
import java.util.Optional;

public interface PaqueteService {

    public PaqueteEntity crearPaquete(PaqueteEntity paquete);
    public Optional<PaqueteEntity> obtenerPaquete(Integer idPaquete);
    public List<PaqueteEntity> listarPaquetes();
    public PaqueteEntity actualizarPaquete(PaqueteEntity paquete);
    public void eliminarPaquete(PaqueteEntity paquete);

}
