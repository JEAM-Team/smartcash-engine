package com.smartcash.engine.exceptions.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ControllerAdvice
public class UsuarioExceptionHandler {

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErrorEntity> emailDuplicadoException(EmailDuplicadoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Email duplicado", e.getMessage(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
    }
}
