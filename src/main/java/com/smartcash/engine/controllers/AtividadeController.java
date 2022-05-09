package com.smartcash.engine.controllers;

import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.services.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    AtividadeService service;

    @GetMapping("/{carteiraId}")
    public ResponseEntity<List<Atividade>> listAtividadeByCarteira(@PathVariable Long carteiraId) {
        List<Atividade> atividades = service.findByCarteiraId(carteiraId);
        return ResponseEntity.status(HttpStatus.OK).body(atividades);
    }
}
