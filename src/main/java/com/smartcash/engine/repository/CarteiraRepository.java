package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
