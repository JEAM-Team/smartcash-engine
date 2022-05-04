package com.smartcash.engine.models.domain;

import com.smartcash.engine.models.enums.TipoConta;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Double valorTotal;

	private TipoConta tipoConta;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;
}