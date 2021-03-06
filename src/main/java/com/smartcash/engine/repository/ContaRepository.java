package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByCarteiraId(Long carteiraId);
}
