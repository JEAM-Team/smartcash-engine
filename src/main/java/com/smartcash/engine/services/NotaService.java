package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    NotaRepository repository;

    @Autowired
    AtividadeService atividadeService;

    public void create(Nota nota) {
        atividadeService.create(Atividade.builder().nota(nota).carteira(nota.getCarteira()).build());
        repository.save(nota);
    }


    public List<Nota> findAll() {
        return repository.findAll();
    }

    public Optional<Nota> findById(Long id) {
        return repository.findById(id);
    }

    public void update(Long id, Nota nota) {
        repository.findById(id).map(not -> {
            nota.setId(id);
            repository.save(nota);
            return nota;
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Nota> findByContaId(Long contaId) {
        return repository.findByContaId(contaId);
    }
}