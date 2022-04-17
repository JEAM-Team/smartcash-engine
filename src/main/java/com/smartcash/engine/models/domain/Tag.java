package com.smartcash.engine.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

}