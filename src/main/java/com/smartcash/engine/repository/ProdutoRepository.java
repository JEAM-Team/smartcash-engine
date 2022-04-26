package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
