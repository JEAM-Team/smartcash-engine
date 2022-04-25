package com.smartcash.engine.models.domain;

import com.smartcash.engine.models.enums.TipoNota;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private Double valor;

	private Boolean repeticao;

	private LocalDateTime data;

	private Integer qtdVezes;

	private TipoNota tipo;

	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
}