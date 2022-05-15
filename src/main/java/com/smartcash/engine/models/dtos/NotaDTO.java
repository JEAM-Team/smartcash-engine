package com.smartcash.engine.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartcash.engine.models.enums.TipoNota;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record NotaDTO(@NotBlank String titulo,
                      @NotNull @Min(0) Double valor,
                      @NotNull Boolean repeticao,
                      @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate data,
                      @NotNull @Min(0) Integer qtdVezes,
                      @NotNull TipoNota tipo,
                      Long tagId,
                      Long produtoId,
                      Long contaId,
                      @NotNull Long carteiraId) {
}
