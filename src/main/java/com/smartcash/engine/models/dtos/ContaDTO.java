package com.smartcash.engine.models.dtos;

import com.smartcash.engine.models.enums.TipoConta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ContaDTO(@NotBlank String nome, @NotBlank Double valorTotal, @NotNull TipoConta tipo, Long carteiraId) {
}
