package com.smartcash.engine.controllers;

import com.smartcash.engine.models.domain.Usuario;
import com.smartcash.engine.models.dtos.UsuarioDTO;
import com.smartcash.engine.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<Void> save(@RequestBody UsuarioDTO usuario) {
        service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Usuario> find(@RequestParam String email) {
        var usuario = service.getByEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Void> update(@PathVariable String email, @RequestParam UsuarioDTO dto) {
        service.update(email, dto);
        return ResponseEntity.ok().build();
    }
}
