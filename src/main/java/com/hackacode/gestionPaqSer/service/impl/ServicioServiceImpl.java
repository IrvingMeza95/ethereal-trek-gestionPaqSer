package com.hackacode.gestionPaqSer.service.impl;

import com.hackacode.gestionPaqSer.entity.ClienteEntity;
import com.hackacode.gestionPaqSer.entity.ServicioEntity;
import com.hackacode.gestionPaqSer.exceptions.MyException;
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
    public ServicioEntity obtenerServicio(Integer idServicio) throws MyException {
        Optional<ServicioEntity> servicio = servicioRepository.findById(idServicio);
        if (servicio.isEmpty())
            throw new MyException("No se há podido cargar los datos del servicio.");
        return servicio.get();
    }

    @Override
    public List<ServicioEntity> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public ServicioEntity actualizarServicio(ServicioEntity nuevoServicio, Integer id) throws MyException {
        ServicioEntity servicio = obtenerServicio(id);
        servicio.setNombre(nuevoServicio.getNombre());
        servicio.setDescripcion(nuevoServicio.getDescripcion());
        servicio.setDestiono(nuevoServicio.getDestiono());
        servicio.setFecha_servicio(nuevoServicio.getFecha_servicio());
        servicio.setCosto(nuevoServicio.getCosto());
        servicio.setTipoServicio(nuevoServicio.getTipoServicio());
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(Integer servicioId) throws MyException {
        ServicioEntity servicio = obtenerServicio(servicioId);
        servicioRepository.delete(servicio);
    }

}
