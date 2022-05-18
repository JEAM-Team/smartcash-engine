package com.smartcash.engine.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartcash.engine.models.enums.TipoConta;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ContaDTO(@NotBlank String nome, @NotNull @Min(0) Double valorTotal, @NotNull @JsonProperty("tipo_conta") TipoConta tipo, @NotNull @JsonProperty("carteira_id") Long carteiraId) {
}
