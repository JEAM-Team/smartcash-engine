package com.smartcash.engine.models.domain;

import com.smartcash.engine.models.enums.TipoCarteira;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public abstract class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	@JoinColumn(name = "conta_id")
	private List<Conta> contas;

	@OneToMany
	@JoinColumn(name = "atividade_id")
	private List<Atividade> historico;

	private TipoCarteira tipo;

	@OneToMany
	@JoinColumn(name = "produto_id")
	private List<Produto> produtos;
}