package com.smartcash.engine.models.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "id_nota")
	private List<Nota> notas;

	private String nome;

	private Double valorTotal;
}