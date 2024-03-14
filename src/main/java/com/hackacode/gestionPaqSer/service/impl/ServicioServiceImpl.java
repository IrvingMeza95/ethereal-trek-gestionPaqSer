package com.hackacode.gestionPaqSer.service.impl;

import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import com.hackacode.gestionPaqSer.repository.ServicioRepository;
import com.hackacode.gestionPaqSer.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public ServicioEntity crearServicio(ServicioEntity servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Optional<ServicioEntity> obtenerServicio(Integer idServicio) {
        return servicioRepository.findById(idServicio);
    }

    @Override
    public List<ServicioEntity> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public ServicioEntity actualizarServicio(ServicioEntity servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(ServicioEntity servicio) {
        servicioRepository.delete(servicio);
    }

}
