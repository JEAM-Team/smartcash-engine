package com.smartcash.engine.models.dtos;

import com.smartcash.engine.models.enums.TipoCarteira;

public record NotaTotal(TipoCarteira tipoCarteira, Double totalSaldo, Double totalPagamento) {
}
