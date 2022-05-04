package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCarteiraId(Long carteiraId);
}
