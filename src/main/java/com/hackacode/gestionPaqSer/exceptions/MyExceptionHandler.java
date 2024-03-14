package com.hackacode.gestionPaqSer.exceptions;

import com.hackacode.gestionPaqSer.responses.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseMessage> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage("Algún atributo de caracter único ya existe en base de datos."));
    }

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ResponseMessage> myExceptionHandler(MyException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
    }

}
