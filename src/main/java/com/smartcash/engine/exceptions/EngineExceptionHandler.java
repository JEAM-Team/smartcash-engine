package com.smartcash.engine.exceptions;

import com.smartcash.engine.exceptions.carteira.BadRequestException;
import com.smartcash.engine.exceptions.usuario.CamposInvalidosException;
import com.smartcash.engine.exceptions.usuario.ErrorEntity;
import com.smartcash.engine.helpers.DateTimeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EngineExceptionHandler {

    @ExceptionHandler(CamposInvalidosException.class)
    public ResponseEntity<ErrorEntity> badRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Campos com problemas no preenchimentoPreencha todos os campos corretamente", e.getMessage(), DateTimeHelper.brazilianTime()));
    }
}
