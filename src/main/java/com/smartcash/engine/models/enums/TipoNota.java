package com.smartcash.engine.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoNota {

	PAGAMENTO("Pagamento"),
	RECEBIMENTO("Recebimento");

	private final String nome;

}