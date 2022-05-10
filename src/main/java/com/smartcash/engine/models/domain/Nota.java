package com.smartcash.engine.models.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartcash.engine.models.enums.TipoNota;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	@Min(value = 0, message = "Apenas valores positivos são permitidos.")
	private Double valor;

	@NotNull
	private Boolean repeticao;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;

	@NotNull
	@Min(value = 0, message = "Apenas valores positivos são permitidos")
	private Integer qtdVezes;

	@NotNull
	private TipoNota tipo;

	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;
}