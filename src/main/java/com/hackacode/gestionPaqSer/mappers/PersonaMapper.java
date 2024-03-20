package com.hackacode.gestionPaqSer.mappers;

import com.hackacode.gestionPaqSer.dtos.PersonaDTO;
import com.hackacode.gestionPaqSer.entities.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    public void fillPersonaDTO(PersonaDTO personaDTO, Persona persona){
        personaDTO.setId(persona.getId());
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setApellido(persona.getApellido());
        personaDTO.setDni(persona.getDni());
        personaDTO.setFechaNac(persona.getFechaNac());
        personaDTO.setPais(persona.getPais());
        personaDTO.setCelular(persona.getCelular());
        personaDTO.setEmail(persona.getEmail());
        personaDTO.setDireccion(persona.getDireccion());
        personaDTO.setEnabled(persona.getEnabled());
        personaDTO.setDireccion(persona.getDireccion());
    }
}
