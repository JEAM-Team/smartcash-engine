package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findByCarteiraId(Long carteiraId);
}
