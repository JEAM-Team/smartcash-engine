package com.smartcash.engine.controllers;

import com.smartcash.engine.helpers.ResponseHelper;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.dtos.EditNota;
import com.smartcash.engine.models.dtos.NotaDTO;
import com.smartcash.engine.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private NotaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid NotaDTO nota) {
        service.create(nota);
    }

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        var notas = service.findAll();
        return ResponseHelper.listResponse(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        var nota = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(nota);
    }

    @GetMapping("/conta/{contaId}")
    public ResponseEntity<List<?>> findByContaId(@PathVariable Long contaId) {
        var notas = service.findByContaId(contaId);
        return ResponseHelper.listResponse(notas);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody @Valid EditNota nota) {
        service.update(id, nota);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/total")
    @ResponseStatus(HttpStatus.OK)
    public CalculaResultadoDto getTotal() {
        return service.calculaTotal();
    }
}
