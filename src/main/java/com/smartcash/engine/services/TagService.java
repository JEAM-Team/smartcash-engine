package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Tag;
import com.smartcash.engine.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepository repository;

    public void create(Tag tag) {
        repository.save(tag);
    }
}
