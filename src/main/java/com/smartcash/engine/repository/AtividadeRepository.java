package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findByCarteiraId(Long carteiraId);
}
