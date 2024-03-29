package com.hackacode.gestionPaqSer.services.impl;

import com.hackacode.gestionPaqSer.entities.File;
import com.hackacode.gestionPaqSer.entities.Paquete;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.repositories.PaqueteRepository;
import com.hackacode.gestionPaqSer.services.FileService;
import com.hackacode.gestionPaqSer.services.PaqueteService;
import com.hackacode.gestionPaqSer.utilities.Utilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PaqueteServiceImpl implements PaqueteService {

    @Autowired
    private PaqueteRepository paqueteRepository;
    @Autowired
    private FileService fileService;

    @Override
    public Paquete crearPaquete(Paquete paquete) {
        calcularPrecio(paquete);
        return paqueteRepository.save(paquete);
    }

    private void calcularPrecio(Paquete paquete) {
        paquete.setPrecio(0D);
        paquete.getListaServicios().forEach(s -> {
            paquete.setPrecio(paquete.getPrecio() +
                    Double.valueOf(paqueteRepository.extraerPrecioDeServicio(s.getIdServicio())));
        });
        paquete.setPrecio(Utilidad.redondear(paquete.getPrecio() * (1 - TipoDeVenta.PAQUETE.getDescuento())));
    }

    @Override
    public Paquete obtenerPaquete(String idPaquete) throws MyException {
        Optional<Paquete> paquete = paqueteRepository.findById(idPaquete);
        if (paquete.isEmpty())
            throw new MyException("No se há podido cargar el paquete.");
        return paquete.get();
    }

    @Override
    public List<Paquete> listarPaquetes() {
        return paqueteRepository.findAll();
    }

    @Override
    public Paquete actualizarPaquete(Paquete nuevoPaquete, String paqueteId) throws MyException {
        Paquete paquete = obtenerPaquete(paqueteId);
        paquete.setListaServicios(nuevoPaquete.getListaServicios());
        paquete.setNombre(nuevoPaquete.getNombre());
        paquete.setDescripcion(nuevoPaquete.getDescripcion());
        calcularPrecio(paquete);
        return paqueteRepository.save(paquete);
    }

    @Override
    public void eliminarPaquete(String paqueteId) throws MyException {
        Paquete paquete = obtenerPaquete(paqueteId);
        paqueteRepository.delete(paquete);
    }

    @Override
    public void actualizarImagenPrincipal(String paqueteId, String imagenId) throws MyException, FileNotFoundException {
        Paquete paquete = obtenerPaquete(paqueteId);
        File file = fileService.getFile(imagenId);
        paquete.setImagenPrincipal(file);
        paqueteRepository.save(paquete);
    }

    @Override
    public void agregarImagen(String paqueteId, String imagenId) throws MyException, FileNotFoundException {
        Paquete paquete = obtenerPaquete(paqueteId);
        File file = fileService.getFile(imagenId);
        paquete.getImagenes().add(file);
        paqueteRepository.save(paquete);
    }

    @Override
    public List<Paquete> filtrarPorNombre(String nombre) {
        return paqueteRepository.filtrarPorNombre(nombre);
    }

    @Override
    public List<Paquete> filtrarPorDescripcion(String descripcion) {
        return paqueteRepository.filtrarPorDescripcion(descripcion);
    }

    @Override
    public List<Paquete> filtrarPorServicio(String idServicio) {
        return paqueteRepository.filtrarPorServicio(idServicio);
    }

    @Override
    public List<Paquete> filtrarPorServicioTipoServicio(String tipo) {
        return paqueteRepository.filtrarPorServicioTipoDeServicio(tipo);
    }

    @Override
    public List<Paquete> filtrarPorServicioDestino(String destino) {
        return paqueteRepository.filtrarPorServicioDestino(destino);
    }

}
