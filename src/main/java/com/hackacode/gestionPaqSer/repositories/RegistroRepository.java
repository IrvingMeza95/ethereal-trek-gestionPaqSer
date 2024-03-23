package com.hackacode.gestionPaqSer.repositories;

import com.hackacode.gestionPaqSer.entities.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, String> {
    @Query(value = "SELECT r FROM Registro r WHERE r.id = ?1 OR r.venta.idVenta = ?1")
    Registro obetenerPorIdPorIdVenta(String param);
    @Query(nativeQuery = true, value =
    "SELECT \n" +
            "    COALESCE(r.medio_pago, 'TOTAL') AS medio_pago,\n" +
            "    SUM(r.total_descuento_paquete) AS total_descuentos, \n" +
            "    SUM(r.total_comision_medio_de_pago) AS total_comisiones, \n" +
            "    SUM(r.total) AS total_por_medio_de_pago\n" +
            "FROM registros r\n" +
            "WHERE YEAR(r.fecha) = ?1 AND MONTH(r.fecha) = ?2\n" +
            "GROUP BY r.medio_pago WITH ROLLUP;")
    List<Object[]> resumenPorAnioMesMedioDePago(String anio, String mes);
    @Query(nativeQuery = true, value =
    "SELECT \n" +
            "    COALESCE(r.tipo_venta, 'TOTAL') AS tipo_venta,\n" +
            "    SUM(r.total_descuento_paquete) AS total_descuentos, \n" +
            "    SUM(r.total_comision_medio_de_pago) AS total_comisiones, \n" +
            "    SUM(r.total) AS total_por_tipo_de_venta\n" +
            "FROM registros r\n" +
            "WHERE YEAR(r.fecha) = ?1 AND MONTH(r.fecha) = ?2\n" +
            "GROUP BY r.tipo_venta WITH ROLLUP;")
    List<Object[]> resumenPorAnioMesTipoDeVenta(String anio, String mes);
    @Query(nativeQuery = true, value =
    "SELECT \n" +
            "\tCOALESCE(s.tipo_servicio, 'TOTAL'), \n" +
            "    SUM(r.total_descuento_paquete) AS total_descuentos, \n" +
            "    SUM(r.total_comision_medio_de_pago) AS total_comisiones, \n" +
            "    SUM(r.total) AS total_por_tipo_de_servicio\n" +
            "FROM registros r\n" +
            "JOIN ventas v ON r.id_venta = v.id_venta\n" +
            "JOIN servicios s ON v.id_servicio = s.id_servicio\n" +
            "WHERE YEAR(r.fecha) = ?1 AND MONTH(r.fecha) = ?2\n" +
            "GROUP BY s.tipo_servicio WITH ROLLUP;")
    List<Object[]> resumenDeServiciosPorAnioMesTipoDeServicio(String anio, String mes);
    @Query(nativeQuery = true, value =
    "SELECT \n" +
            "\tCOALESCE(s.tipo_servicio, 'TOTAL'), \n" +
            "    SUM(r.total_descuento_paquete) AS total_descuentos, \n" +
            "    SUM(r.total_comision_medio_de_pago) AS total_comisiones, \n" +
            "    SUM(r.total) AS total_por_tipo_de_servicio\n" +
            "FROM registros r\n" +
            "JOIN ventas v ON r.id_venta = v.id_venta\n" +
            "JOIN paquetes p ON v.id_paquete = p.id_paquete\n" +
            "JOIN detalle_paquete_servicio dps ON p.id_paquete = dps.id_paquete\n" +
            "JOIN servicios s ON dps.id_servicio = s.id_servicio\n" +
            "WHERE YEAR(r.fecha) = ?1 AND MONTH(r.fecha) = ?2\n" +
            "GROUP BY s.tipo_servicio WITH ROLLUP;")
    List<Object[]> resumenDePaquetesPorAnioMesTipoDeServicio(String anio, String mes);
}
