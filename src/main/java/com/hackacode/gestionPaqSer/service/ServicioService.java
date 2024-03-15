package com.hackacode.gestionPaqSer.service;

import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    ServicioEntity crearServicio(ServicioEntity servicio);
    ServicioEntity obtenerServicio(String idServicio) throws MyException;
    List<ServicioEntity> listarServicios();
    ServicioEntity actualizarServicio(ServicioEntity nuevoServicio, String id) throws MyException;
    void eliminarServicio(String servicioId) throws MyException;
}
