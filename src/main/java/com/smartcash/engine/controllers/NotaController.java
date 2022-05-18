package com.smartcash.engine.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartcash.engine.helpers.ResponseHelper;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.dtos.CalculaResultadoDto;
import com.smartcash.engine.models.dtos.EditNota;
import com.smartcash.engine.models.dtos.NotaDTO;
import com.smartcash.engine.models.dtos.NotaTotalFilter;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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
    public ResponseEntity<List<?>> findByContaId(@PathVariable Long contaId, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        var notas = service.findByContaId(contaId, start, end);
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
    public CalculaResultadoDto getTotal(@RequestParam(required = false, name = "saldo_pessoal") Boolean saldoPessoal, @RequestParam(required = false, name = "saldo_comercial") Boolean saldoComercial, @RequestParam(required = false, name = "pagamento_pessoal") Boolean pagamentoPessoal, @RequestParam(required = false, name = "pagamento_comercial") Boolean pagamentoComercial, @RequestHeader String email) {
        return service.calculaTotal(saldoPessoal, saldoComercial, pagamentoPessoal, pagamentoComercial, email);
    }
}
