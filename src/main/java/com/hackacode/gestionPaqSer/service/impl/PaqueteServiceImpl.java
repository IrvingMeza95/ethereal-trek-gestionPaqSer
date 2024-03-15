package com.hackacode.gestionPaqSer.service.impl;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.repository.PaqueteRepository;
import com.hackacode.gestionPaqSer.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaqueteServiceImpl implements PaqueteService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Override
    public PaqueteEntity crearPaquete(PaqueteEntity paquete) {
        return paqueteRepository.save(paquete);
    }

    @Override
    public PaqueteEntity obtenerPaquete(Integer idPaquete) throws MyException {
        Optional<PaqueteEntity> paquete = paqueteRepository.findById(idPaquete);
        if (paquete.isEmpty())
            throw new MyException("No se há podido cargar el paquete.");
        return paquete.get();
    }

    @Override
    public List<PaqueteEntity> listarPaquetes() {
        return paqueteRepository.findAll();
    }

    @Override
    public PaqueteEntity actualizarPaquete(PaqueteEntity nuevoPaquete, Integer paqueteId) throws MyException {
        PaqueteEntity paquete = obtenerPaquete(paqueteId);
        paquete.setListaServicios(nuevoPaquete.getListaServicios());
        paquete.setNombre(nuevoPaquete.getNombre());
        paquete.setDescripcion(nuevoPaquete.getDescripcion());
        paquete.setPrecio(nuevoPaquete.getPrecio());
        return paqueteRepository.save(paquete);
    }

    @Override
    public void eliminarPaquete(Integer paqueteId) throws MyException {
        PaqueteEntity paquete = obtenerPaquete(paqueteId);
        paqueteRepository.delete(paquete);
    }

}
