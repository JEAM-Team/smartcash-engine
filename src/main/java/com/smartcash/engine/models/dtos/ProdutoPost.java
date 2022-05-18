package com.smartcash.engine.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ProdutoPost(
        @NotBlank String nome,
        @NotBlank String codigo,
        @NotBlank String descricao,
        @NotNull @Min(0) Double valor,
        @NotNull @JsonProperty("carteira_id") Long carteiraId) {
}
