package com.hackacode.gestionPaqSer.service;

import com.hackacode.gestionPaqSer.entity.ServicioEntity;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    public ServicioEntity crearServicio(ServicioEntity servicio);
    public Optional<ServicioEntity> obtenerServicio(Integer idServicio);
    public List<ServicioEntity> listarServicios();
    public ServicioEntity actualizarServicio(ServicioEntity servicio);
    public void eliminarServicio(ServicioEntity servicio);

}
