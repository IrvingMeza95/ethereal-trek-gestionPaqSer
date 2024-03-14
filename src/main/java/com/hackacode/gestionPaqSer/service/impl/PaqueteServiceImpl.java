package com.hackacode.gestionPaqSer.service.impl;

import com.hackacode.gestionPaqSer.entity.PaqueteEntity;
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
    public Optional<PaqueteEntity> obtenerPaquete(Integer idPaquete) {
        return paqueteRepository.findById(idPaquete);
    }

    @Override
    public List<PaqueteEntity> listarPaquetes() {
        return paqueteRepository.findAll();
    }

    @Override
    public PaqueteEntity actualizarPaquete(PaqueteEntity paquete) {
        return paqueteRepository.save(paquete);
    }

    @Override
    public void eliminarPaquete(PaqueteEntity paquete) {
        paqueteRepository.delete(paquete);
    }

}
