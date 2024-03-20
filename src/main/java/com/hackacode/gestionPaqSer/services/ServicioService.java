package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.ServicioEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ServicioService {
    ServicioEntity crearServicio(ServicioEntity servicio);
    ServicioEntity obtenerServicio(String idServicio) throws MyException;
    List<ServicioEntity> listarServicios();
    ServicioEntity actualizarServicio(ServicioEntity nuevoServicio, String id) throws MyException;
    void eliminarServicio(String servicioId) throws MyException;
    void actualizarImagenPrincipal(String servicioId, String imagenId) throws FileNotFoundException, MyException;
    void agregarImagen(String servicioId, String imagenId) throws MyException, FileNotFoundException;
}
