package com.hackacode.gestionPaqSer.clientes;

import com.hackacode.gestionPaqSer.entity.ClienteEntity;
import com.hackacode.gestionPaqSer.entity.EmpleadoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("gestionEmpCli")
public interface UsuariosFeign {
    @GetMapping("/clientes/{param}")
    public ClienteEntity obtenerCliente(@PathVariable String param);
    @GetMapping("/clientes")
    public List<ClienteEntity> listarClientes();
    @GetMapping("/empleados/{param}")
    public EmpleadoEntity obtenerEmpleado(@PathVariable String param);
    @GetMapping("/empleados")
    public List<EmpleadoEntity> listarEmpleados();
}
