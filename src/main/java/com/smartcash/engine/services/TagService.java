package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.models.domain.Tag;
import com.smartcash.engine.models.dtos.TagPost;
import com.smartcash.engine.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepository repository;

    public void create(TagPost dto) {
        var tag = new Tag();
        tag.setTitulo(dto.titulo());
        repository.save(tag);
    }

    public Tag findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não pode ser encontrado nem uma Tag com o ID: " + id));
    }
}
