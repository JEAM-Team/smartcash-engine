package com.smartcash.engine.models.dtos;

import lombok.Builder;
import lombok.Data;

public record CalculaResultadoDto(NotaTotal totalPessoal, NotaTotal totalComercial){
}
