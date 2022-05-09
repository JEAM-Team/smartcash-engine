package com.smartcash.engine.controllers;

import com.smartcash.engine.models.domain.Carteira;
import com.smartcash.engine.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    CarteiraService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Carteira carteira) {
        service.create(carteira);
    }

    @GetMapping
    public ResponseEntity<List<Carteira>> findAll() {
        List<Carteira> carteiras = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(carteiras);
    }

}
