package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.models.domain.Tag;
import com.smartcash.engine.models.dtos.TagPost;
import com.smartcash.engine.models.dtos.UsuarioDTO;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    TagRepository repository;

    public void create(TagPost dto) {
        var tag = new Tag();
        tag.setTitulo(dto.titulo());
        var carteira = carteiraService.findById(dto.carteiraId());
        tag.setCarteira(carteira);
        repository.save(tag);
    }

    public Tag findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não pode ser encontrado nem uma Tag com o ID: " + id));
    }

    public List<Tag> findByEmail(String email, TipoCarteira tipo){
        var usuario = usuarioService.getByEmail(email);
        return usuario.getCarteiras().stream().filter(c -> tipo.equals(c.getTipo())).findFirst().get().getTags();
    }
}
