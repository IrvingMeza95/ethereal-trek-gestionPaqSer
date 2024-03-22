package com.hackacode.gestionPaqSer.controllers;

import com.hackacode.gestionPaqSer.dtos.RegistroDTO;
import com.hackacode.gestionPaqSer.exceptions.MyException;
import com.hackacode.gestionPaqSer.mappers.RegistroMapper;
import com.hackacode.gestionPaqSer.services.RegistroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
@Slf4j
public class RegistroController {

    @Autowired
    private RegistroService registroService;
    @Autowired
    private RegistroMapper registroMapper;

    @GetMapping("/{param}")
    public ResponseEntity<RegistroDTO> busucar(@PathVariable String param) throws MyException {
        return ResponseEntity.ok(registroMapper.getRegistroDTO(registroService.obtenerRegistro(param)));
    }

    @GetMapping
    public ResponseEntity<List<RegistroDTO>> listarRegistros() throws MyException {
        return ResponseEntity.ok(registroMapper.getListaRegistroDTO(registroService.listarRegistros()));
    }

}
