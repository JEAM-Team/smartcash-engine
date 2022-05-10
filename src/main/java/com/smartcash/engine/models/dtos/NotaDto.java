package com.smartcash.engine.models.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotaDto {

    private String titulo;

    private Double valor;

    private LocalDate data;
}
