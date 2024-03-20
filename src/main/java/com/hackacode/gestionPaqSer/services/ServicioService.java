package com.hackacode.gestionPaqSer.services;

import com.hackacode.gestionPaqSer.entities.Servicio;
import com.hackacode.gestionPaqSer.exceptions.MyException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ServicioService {
    Servicio crearServicio(Servicio servicio);
    Servicio obtenerServicio(String idServicio) throws MyException;
    List<Servicio> listarServicios();
    Servicio actualizarServicio(Servicio nuevoServicio, String id) throws MyException;
    void eliminarServicio(String servicioId) throws MyException;
    void actualizarImagenPrincipal(String servicioId, String imagenId) throws FileNotFoundException, MyException;
    void agregarImagen(String servicioId, String imagenId) throws MyException, FileNotFoundException;
}
