package com.smartcash.engine.models.domain;

import com.smartcash.engine.models.enums.TipoCarteira;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private TipoCarteira tipo;

	@OneToMany(mappedBy = "carteira")
	private List<Conta> contas;
}