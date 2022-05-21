package com.smartcash.engine.models.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smartcash.engine.models.enums.TipoCarteira;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteira tipo;

	@OneToMany(mappedBy = "carteira")
	@JsonManagedReference
	@ToString.Exclude
	private List<Conta> contas;

	@OneToMany(mappedBy = "carteira")
	@JsonManagedReference
	@ToString.Exclude
	private List<Tag> tags;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Carteira carteira = (Carteira) o;
		return id != null && Objects.equals(id, carteira.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}