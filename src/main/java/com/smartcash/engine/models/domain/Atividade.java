package com.smartcash.engine.models.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nota_id")
	private Nota nota;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Atividade atividade = (Atividade) o;
		return id != null && Objects.equals(id, atividade.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}