package com.smartcash.engine.models.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartcash.engine.models.enums.TipoNota;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private Double valor;

	private Boolean repeticao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;

	private Integer qtdVezes;

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