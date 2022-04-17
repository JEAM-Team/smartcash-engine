package com.smartcash.engine.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoConta {

	CC("Conta Corrente"),
	CS("Conta salário"),
	CP("Conta Poupança"),
	DN("Dinheiro");

	private final String nome;
}