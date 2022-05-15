package com.smartcash.engine.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculaResultadoDto {

    private Double totalPagamento;
    private Double totalRecebimento;
}
