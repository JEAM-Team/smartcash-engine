package com.smartcash.engine.controllers;

import com.smartcash.engine.helpers.ResponseHelper;
import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Conta;
import com.smartcash.engine.models.dtos.ContaDTO;
import com.smartcash.engine.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ContaDTO conta) {
        service.create(conta);
    }

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        var contas = service.findAll();
        return ResponseHelper.listResponse(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> findById(@PathVariable Long id) {
        Conta conta = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(conta);
    }
    @GetMapping("/carteira")
    public ResponseEntity<List<?>> listContaByCarteira(@PathVariable Long carteiraId) {
        var contas = service.findByCarteiraId(carteiraId);
        return ResponseHelper.listResponse(contas);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody ContaDTO conta) {
        service.update(id, conta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
