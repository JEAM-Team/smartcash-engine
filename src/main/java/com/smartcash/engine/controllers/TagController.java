package com.smartcash.engine.controllers;

import com.smartcash.engine.helpers.ResponseHelper;
import com.smartcash.engine.models.dtos.TagPost;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired

    TagService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTag(@RequestBody TagPost tag) {
        service.create(tag);
    }
    @GetMapping("/busca")
    public ResponseEntity<List<?>> buscarTagPorEmail(@RequestParam("tipo_carteira") TipoCarteira tipo, @RequestHeader String email) {
        var tags = service.findByEmail(email, tipo);
        return ResponseHelper.listResponse(tags);
    }
}
