package com.smartcash.engine.controllers;

import com.smartcash.engine.helpers.ResponseHelper;
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

    @GetMapping("/{carteira_id}")
    public ResponseEntity<List<?>> listAtividadeByCarteira(@PathVariable(name = "carteira_id") Long carteiraId) {
        var atividades = service.findByCarteiraId(carteiraId);
        return ResponseHelper.listResponse(atividades);
    }
}
