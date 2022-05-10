package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Produto;
import com.smartcash.engine.models.dtos.ProdutoDto;
import com.smartcash.engine.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public void create(Produto produto) {
        repository.save(produto);
    }

    public List<ProdutoDto> findAll() {
        List<Produto> produtos = repository.findAll();
        List<ProdutoDto> produtoDtos = new ArrayList<>();
        produtos.forEach(produto -> {
            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setNome(produto.getNome());
            produtoDto.setValor(produto.getValor());
            produtoDto.setCodigo(produto.getCodigo());
            produtoDtos.add(produtoDto);
        });
        return produtoDtos;
    }

    public List<Produto> findByCarteiraId(Long carteiraId) {
        return repository.findByCarteiraId(carteiraId);
    }

    public Optional<Produto> findById(Long id) {
        return repository.findById(id);
    }

    public void update(Long id, Produto produto) {
        repository.findById(id).map(prod -> {
            produto.setId(id);
            repository.save(produto);
            return produto;
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
