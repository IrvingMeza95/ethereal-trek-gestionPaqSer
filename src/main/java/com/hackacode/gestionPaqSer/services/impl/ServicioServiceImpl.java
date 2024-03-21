package com.hackacode.gestionPaqSer.services.impl;

import com.hackacode.gestionPaqSer.entities.File;
import com.hackacode.gestionPaqSer.entities.Servicio;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.repositories.ServicioRepository;
import com.hackacode.gestionPaqSer.services.FileService;
import com.hackacode.gestionPaqSer.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private FileService fileService;

    @Override
    public Servicio crearServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio obtenerServicio(String idServicio) throws MyException {
        Optional<Servicio> servicio = servicioRepository.findById(idServicio);
        if (servicio.isEmpty())
            throw new MyException("No se há podido cargar los datos del servicio.");
        return servicio.get();
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio actualizarServicio(Servicio nuevoServicio, String id) throws MyException {
        Servicio servicio = obtenerServicio(id);
        servicio.setDescripcion(nuevoServicio.getDescripcion());
        servicio.setDestiono(nuevoServicio.getDestiono());
        servicio.setFechaServicio(nuevoServicio.getFechaServicio());
        servicio.setCosto(nuevoServicio.getCosto());
        servicio.setTipoServicio(nuevoServicio.getTipoServicio());
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(String servicioId) throws MyException {
        Servicio servicio = obtenerServicio(servicioId);
        servicioRepository.delete(servicio);
    }

    @Override
    public void actualizarImagenPrincipal(String servicioId, String imagenId) throws FileNotFoundException, MyException {
        Servicio servicio = obtenerServicio(servicioId);
        File file = fileService.getFile(imagenId);
        servicio.setImagenPrincipal(file);
        servicioRepository.save(servicio);
    }

    @Override
    public void agregarImagen(String servicioId, String imagenId) throws MyException, FileNotFoundException {
        Servicio servicio = obtenerServicio(servicioId);
        File file = fileService.getFile(imagenId);
        servicio.getImagenes().add(file);
        servicioRepository.save(servicio);
    }

    @Override
    public List<Servicio> filtrarPorTipoDeServicio(String tipo) {
        return servicioRepository.filtrarPorTipoDeServicio(tipo);
    }

    @Override
    public List<Servicio> filtrarPorDescripcion(String descripcion) {
        return servicioRepository.filtrarPorDescripcion(descripcion);
    }

    @Override
    public List<Servicio> filtrarPorDestino(String destino) {
        return servicioRepository.filtrarPorDestino(destino);
    }

}
