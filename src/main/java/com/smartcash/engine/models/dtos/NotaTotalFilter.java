package com.smartcash.engine.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartcash.engine.models.enums.TipoCarteira;

public record NotaTotalFilter(@JsonProperty("tipo_carteira") TipoCarteira tipoCarteira, Boolean saldo, Boolean pagamento) {
}
