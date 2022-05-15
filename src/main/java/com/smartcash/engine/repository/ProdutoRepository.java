package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCarteiraId(Long carteiraId);
}
