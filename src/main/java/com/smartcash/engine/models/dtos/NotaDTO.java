package com.smartcash.engine.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartcash.engine.models.enums.TipoNota;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record NotaDTO(@NotBlank String titulo,
                      @NotNull @Min(0) Double valor,
                      @NotNull Boolean repeticao,
                      @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate data,
                      @NotNull @Min(0) @JsonProperty("qtd_vezes") Integer qtdVezes,
                      @NotNull TipoNota tipo,
                      @JsonProperty("tag_id") Long tagId,
                      @JsonProperty("produto_id") Long produtoId,
                      @NotNull @JsonProperty("conta_id") Long  contaId,
                      @NotNull @JsonProperty("carteira_id") Long carteiraId) {
}
