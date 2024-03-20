package com.hackacode.gestionPaqSer.clientes;

import com.hackacode.gestionPaqSer.entities.Cliente;
import com.hackacode.gestionPaqSer.entities.Empleado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("gestionEmpCli")
public interface UsuariosFeign {
    @GetMapping("/clientes/{param}")
    public Cliente obtenerCliente(@PathVariable String param);
    @GetMapping("/clientes")
    public List<Cliente> listarClientes();
    @GetMapping("/empleados/{param}")
    public Empleado obtenerEmpleado(@PathVariable String param);
    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados();
}
