package com.smartcash.engine.exceptions.usuario;

import com.smartcash.engine.helpers.DateTimeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ControllerAdvice
public class UsuarioExceptionHandler {

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErrorEntity> emailDuplicadoException(EmailDuplicadoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Email duplicado", e.getMessage(), DateTimeHelper.brazilianTime()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorEntity> usernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("O email informado não pode ser encontrado", e.getMessage(), DateTimeHelper.brazilianTime()));
    }

    @ExceptionHandler(CamposInvalidosException.class)
    public ResponseEntity<ErrorEntity> camposInvalidosException(CamposInvalidosException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Preencha todos os campos corretamente", e.getMessage(), DateTimeHelper.brazilianTime()));
    }
}
