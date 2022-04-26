package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
