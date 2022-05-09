package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByContaId(Long idConta);
}
