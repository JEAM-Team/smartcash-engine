package com.smartcash.engine.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCarteira {

	PESSOAL("Pessoal"),
	COMERCIAL("Comercial");

	private final String nome;

}