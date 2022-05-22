package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AtividadeService {

    @Autowired
    AtividadeRepository repository;

    public void create(Atividade atividade) {
        repository.save(atividade);
    }

    public List<Atividade> findByCarteiraId(Long carteiraId, LocalDate start, LocalDate end) {
        return repository.
                findByCarteiraIdAndNotaDataBetween(carteiraId, start, end)
                .stream().sorted(Comparator.comparing((Atividade a) -> a.getNota().getData()).reversed())
                .toList();
    }

}
