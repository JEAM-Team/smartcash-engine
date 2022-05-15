package com.smartcash.engine.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.smartcash.engine.helpers.ResponseHelper;
import com.smartcash.engine.models.domain.Carteira;
import com.smartcash.engine.models.dtos.ContaComercialPost;
import com.smartcash.engine.services.CarteiraService;
import com.smartcash.engine.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    CarteiraService service;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Carteira carteira) {
        service.create(carteira);
    }

    @PostMapping("/comercial")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ContaComercialPost comercialPost) {
        usuarioService.update(comercialPost);
    }

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        var carteiras = service.findAll();
        return ResponseHelper.listResponse(carteiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> findById(@PathVariable Long id) {
        var carteiras = service.findById(id);
        return ResponseEntity.ok(carteiras);
    }

}
