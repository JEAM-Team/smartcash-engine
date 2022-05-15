package com.smartcash.engine.models.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartcash.engine.models.enums.TipoConta;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@Min(0)
	private Double valorTotal;

	@JsonProperty("tipo_conta")
	private TipoConta tipo;

	@ManyToOne
	@JoinColumn(name = "carteira_id")
	@JsonBackReference
	private Carteira carteira;

	@OneToMany(mappedBy = "conta")
	@JsonManagedReference
	@ToString.Exclude
	private List<Nota> notas;

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