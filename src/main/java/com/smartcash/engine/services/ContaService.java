package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Conta;
import com.smartcash.engine.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    ContaRepository repository;

    public void create(Conta conta) {
        repository.save(conta);
    }

    public List<Conta> findAll() {
        return repository.findAll();
    }

    public Optional<Conta> findById(Long id) {
        return repository.findById(id);
    }

    public List<Conta> findByCarteiraId(Long carteiraId) {
        return repository.findByCarteiraId(carteiraId);
    }

    public void update(Long id, Conta conta) {
        repository.findById(id).map(not -> {
            conta.setId(id);
            repository.save(conta);
            return conta;
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
