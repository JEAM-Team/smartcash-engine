package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Atividade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AtividadeRepository extends PagingAndSortingRepository<Atividade, Long> {

    List<Atividade> findByCarteiraIdAndNotaDataBetween(Long carteiraId, LocalDate start, LocalDate end);
}
