package com.smartcash.engine.models.domain;

import com.smartcash.engine.models.enums.TipoConta;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@Min(0)
	private Double valorTotal;

	@NotNull
	private TipoConta tipoConta;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;
}