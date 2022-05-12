package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.models.domain.Produto;
import com.smartcash.engine.models.dtos.EditProduto;
import com.smartcash.engine.models.dtos.ProdutoPost;
import com.smartcash.engine.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private CarteiraService carteiraService;
    @Autowired
    ProdutoRepository repository;

    public void create(ProdutoPost dto) {
        var carteira = carteiraService.findById(dto.carteiraId());
        var produto = new Produto();
        BeanUtils.copyProperties(dto, produto, "id, carteiraId");
        produto.setCarteira(carteira);
        repository.save(produto);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public List<Produto> findByCarteiraId(Long carteiraId) {
        return repository.findByCarteiraId(carteiraId);
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi possível encontrar o produto com o ID: " + id)
        );
    }

    public void update(Long id, EditProduto dto) {
        var produto = findById(id);
        BeanUtils.copyProperties(dto, produto);
        repository.save(produto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
