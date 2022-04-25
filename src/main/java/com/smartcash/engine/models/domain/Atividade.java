package com.smartcash.engine.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "nota_id")
	private Nota nota;
}