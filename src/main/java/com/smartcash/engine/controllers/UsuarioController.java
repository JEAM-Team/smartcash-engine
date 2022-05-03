package com.smartcash.engine.controllers;

import com.smartcash.engine.models.domain.Usuario;
import com.smartcash.engine.models.dtos.UsuarioDTO;
import com.smartcash.engine.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> save(@RequestBody UsuarioDTO usuario) {
        service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
