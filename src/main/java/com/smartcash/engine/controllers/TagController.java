package com.smartcash.engine.controllers;

import com.smartcash.engine.models.domain.Tag;
import com.smartcash.engine.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired

    TagService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTag(@RequestBody Tag tag) {
        service.create(tag);
    }
}
