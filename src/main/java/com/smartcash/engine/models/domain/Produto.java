package com.smartcash.engine.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String codigo;

	private String descricao;

	private Double valor;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;
}