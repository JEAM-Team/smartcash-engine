package com.smartcash.engine.models.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String codigo;

	@NotBlank
	private String descricao;

	@NotNull
	@Min(0)
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;
}