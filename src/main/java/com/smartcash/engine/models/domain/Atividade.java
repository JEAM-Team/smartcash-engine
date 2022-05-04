package com.smartcash.engine.models.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "nota_id")
	private Nota nota;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;
}