package com.smartcash.engine.exceptions;

import br.com.caelum.stella.validation.InvalidStateException;
import com.smartcash.engine.exceptions.carteira.BadRequestException;
import com.smartcash.engine.exceptions.usuario.CamposInvalidosException;
import com.smartcash.engine.exceptions.usuario.ErrorEntity;
import com.smartcash.engine.helpers.DateTimeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EngineExceptionHandler {

    @ExceptionHandler(CamposInvalidosException.class)
    public ResponseEntity<ErrorEntity> badRequestException(CamposInvalidosException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Campos com problemas no preenchimento. Preencha todos os campos corretamente", e.getMessage(), DateTimeHelper.brazilianTime()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorEntity> badRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Campos com problemas no preenchimento. Preencha todos os campos corretamente", e.getMessage(), DateTimeHelper.brazilianTime()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorEntity> notFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Campos com problemas no preenchimento. Preencha todos os campos corretamente", e.getMessage(), DateTimeHelper.brazilianTime()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorEntity> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Campos com problemas no preenchimento. Preencha todos os campos corretamente", e.getMessage(), DateTimeHelper.brazilianTime()));
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<ErrorEntity> invalidStateException(InvalidStateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Ocorreu um erro ao processar a sua solicitação.", e.getMessage(), DateTimeHelper.brazilianTime()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorEntity> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity("Existem campos obrigatórios não informados.", e.getMessage(), DateTimeHelper.brazilianTime()));
    }
}
