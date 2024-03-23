package com.hackacode.gestionPaqSer.services.impl;

import com.hackacode.gestionPaqSer.entities.Registro;
import com.hackacode.gestionPaqSer.entities.Venta;
import com.hackacode.gestionPaqSer.enums.MediosDePago;
import com.hackacode.gestionPaqSer.enums.TipoDeVenta;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.repositories.RegistroRepository;
import com.hackacode.gestionPaqSer.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public Registro guardar(Venta venta) {
        Registro registro = new Registro();
        return registroRepository.save(mapearRegistro(registro, venta));
    }

    @Override
    public Registro obtenerRegistro(String param) throws MyException {
        Optional<Registro> registro = Optional.of(registroRepository.obetenerPorIdPorIdVenta(param));
        if (registro.isEmpty())
            throw new MyException("No se encontró el registro.");
        return registro.get();
    }

    @Override
    public List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }

    @Override
    public Registro actualizar(Venta venta) throws MyException {
        Registro registroDb = obtenerRegistro(venta.getIdVenta());
        return registroRepository.save(mapearRegistro(registroDb, venta));
    }

    @Override
    public void eliminar(String param) throws MyException {
        Registro registro = obtenerRegistro(param);
        registroRepository.delete(registro);
    }

    @Override
    public List<Object[]> resumenPorAnioMesMedioDePago(String anio, String mes) {
        List<Object[]> registros = registroRepository.resumenPorAnioMesMedioDePago(anio,mes);
        for (Object[] row : registros){
            for (Object o : row) {
                System.out.print(o + " ");
            }
            System.out.println("");
        }
        return registros;
    }

    @Override
    public List<Object[]> resumenPorAnioMesTipoDeVenta(String anio, String mes) {
        List<Object[]> registros = registroRepository.resumenPorAnioMesTipoDeVenta(anio,mes);
        for (Object[] row : registros){
            for (Object o : row) {
                System.out.print(o + " ");
            }
            System.out.println("");
        }
        return registros;
    }

    @Override
    public List<Object[]> resumenDeServiciosPorAnioMesTipoDeServicio(String anio, String mes) {
        List<Object[]> registros = registroRepository.resumenDeServiciosPorAnioMesTipoDeServicio(anio,mes);
        for (Object[] row : registros){
            for (Object o : row) {
                System.out.print(o + " ");
            }
            System.out.println("");
        }
        return registros;
    }

    @Override
    public List<Object[]> resumenDePaquetesPorAnioMesTipoDeServicio(String anio, String mes) {
        List<Object[]> registros = registroRepository.resumenDePaquetesPorAnioMesTipoDeServicio(anio,mes);
        for (Object[] row : registros){
            for (Object o : row) {
                System.out.print(o + " ");
            }
            System.out.println("");
        }
        return registros;
    }

    public Registro mapearRegistro(Registro registro, Venta venta){
        registro.setVenta(venta);
        if (venta.getTipoVenta().equalsIgnoreCase(TipoDeVenta.PAQUETE.name())){
            registro.setPorcentajeDescuentoPaquete(TipoDeVenta.PAQUETE.getDescuento());
            registro.setTotalDescuentoPaquete(((venta.getTotal() * 100)/
                    ((1 - TipoDeVenta.PAQUETE.getDescuento())*100))- venta.getTotal());
        }else{
            registro.setPorcentajeDescuentoPaquete(0D);
            registro.setTotalDescuentoPaquete(0D);
        }
        registro.setTipoVenta(venta.getTipoVenta());
        registro.setFecha(venta.getFechaVenta());
        registro.setMedioPago(venta.getMedioPago());
        registro.setPorcentajeComisionMedioDePago(Objects.requireNonNull(MediosDePago.getMedioDePago
                (venta.getMedioPago())).getComision());
        registro.setTotalComisionMedioDePago(venta.getTotal() - (venta.getTotal()/(1 + registro.getPorcentajeComisionMedioDePago())));
        registro.setTotal(venta.getTotal());
        return registro;
    }

}
