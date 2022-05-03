package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByContaId(Long idConta);
}
