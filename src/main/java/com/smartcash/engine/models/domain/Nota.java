package com.smartcash.engine.models.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartcash.engine.models.enums.TipoNota;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	@Min(value = 0, message = "Apenas valores positivos são permitidos.")
	private Double valor;

	@NotNull
	private Boolean repeticao;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;

	@NotNull
	@Min(value = 0, message = "Apenas valores positivos são permitidos")
	private Integer qtdVezes;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoNota tipo;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "conta_id")
	private Conta conta;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Nota nota = (Nota) o;
		return id != null && Objects.equals(id, nota.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}