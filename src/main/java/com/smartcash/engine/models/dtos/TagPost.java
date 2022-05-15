package com.smartcash.engine.models.dtos;

import javax.validation.constraints.NotBlank;

public record TagPost(@NotBlank String titulo) {
}
