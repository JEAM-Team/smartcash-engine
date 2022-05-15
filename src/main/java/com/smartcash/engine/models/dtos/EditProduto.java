package com.smartcash.engine.models.dtos;

public record EditProduto(
        String nome,
        String codigo,
        String descricao,
        Double valor) {
}
