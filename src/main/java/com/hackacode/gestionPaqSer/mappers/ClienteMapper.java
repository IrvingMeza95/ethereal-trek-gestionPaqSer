package com.hackacode.gestionPaqSer.mappers;

import com.hackacode.gestionPaqSer.dtos.ClienteDTO;
import com.hackacode.gestionPaqSer.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    @Autowired
    private PersonaMapper personaMapper;

    public ClienteDTO getClienteDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        personaMapper.fillPersonaDTO(clienteDTO, cliente);
        return clienteDTO;
    }

    public List<ClienteDTO> getListClienteDTO(List<Cliente> clientes){
        return clientes.stream().map(this::getClienteDTO).collect(Collectors.toList());
    }

}
