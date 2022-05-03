package com.smartcash.engine.controller;

import com.smartcash.engine.models.domain.Conta;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Conta conta) {
        service.create(conta);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> findAll() {
        List<Conta> contas = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Conta>> findById(@PathVariable Long id) {
        Optional<Conta> conta = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(conta);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody Conta conta) {
        service.update(id, conta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
