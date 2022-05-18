package com.smartcash.engine.controllers;

import com.smartcash.engine.helpers.ResponseHelper;
import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.services.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    AtividadeService service;

    @GetMapping("/{carteira_id}")
    public ResponseEntity<List<?>> listAtividadeByCarteira(@PathVariable(name = "carteira_id") Long carteiraId, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        var atividades = service.findByCarteiraId(carteiraId, start, end);
        return ResponseHelper.listResponse(atividades);
    }
}
