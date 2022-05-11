package com.smartcash.engine.models.domain;

import com.smartcash.engine.models.enums.TipoConta;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Conta conta = (Conta) o;
		return id != null && Objects.equals(id, conta.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}