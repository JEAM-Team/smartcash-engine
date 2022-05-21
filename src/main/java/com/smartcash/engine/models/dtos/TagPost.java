package com.smartcash.engine.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TagPost(@NotBlank String titulo, @NotNull @JsonProperty("carteira_id") Long carteiraId) {
}
