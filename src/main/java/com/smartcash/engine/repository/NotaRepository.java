package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
}
