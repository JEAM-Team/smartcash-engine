package com.smartcash.engine.controller;

import com.smartcash.engine.models.domain.Tag;
import com.smartcash.engine.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired

    TagService service;

    @PostMapping
    public void createTag(Tag tag) {
        service.create(tag);
        ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
