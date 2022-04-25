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

	@OneToMany(mappedBy = "carteira")
	private List<Conta> contas;

	@OneToMany(mappedBy = "carteira")
	private List<Atividade> historico;

	private TipoCarteira tipo;

	@OneToMany(mappedBy = "carteira")
	private List<Produto> produtos;
}