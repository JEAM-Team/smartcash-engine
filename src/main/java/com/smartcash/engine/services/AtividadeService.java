package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    AtividadeRepository repository;

    public void create(Atividade atividade) {
        repository.save(atividade);
    }

    public List<Atividade> findByCarteiraId(Long carteiraId, LocalDate start, LocalDate end) {
        return repository.findByCarteiraIdAndNotaDataBetween(carteiraId, start, end);
    }

}
