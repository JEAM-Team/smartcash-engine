package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
