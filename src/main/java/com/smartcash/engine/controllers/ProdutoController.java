package com.smartcash.engine.controllers;

import com.smartcash.engine.helpers.ResponseHelper;
import com.smartcash.engine.models.domain.Produto;
import com.smartcash.engine.models.dtos.EditProduto;
import com.smartcash.engine.models.dtos.ProdutoPost;
import com.smartcash.engine.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProdutoPost produto) {
        service.create(produto);
    }

    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        var produtos = service.findAll();
        return ResponseHelper.listResponse(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto produto = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody EditProduto produto) {
        service.update(id, produto);
    }

    @GetMapping("/carteira/{carteira_id}")
    public ResponseEntity<List<?>> listProdutoByCarteira(@PathVariable("carteira_id") Long carteiraId) {
        var produtos = service.findByCarteiraId(carteiraId);
        return ResponseHelper.listResponse(produtos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
