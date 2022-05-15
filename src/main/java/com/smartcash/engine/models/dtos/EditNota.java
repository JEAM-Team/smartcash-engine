package com.smartcash.engine.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record EditNota(
        Double valor,
        Boolean repeticao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate data,
        Integer qtdVezes,
        Long carteiraId
) {}
