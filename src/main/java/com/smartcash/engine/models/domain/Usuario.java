package com.smartcash.engine.models.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private String senha;

    @OneToMany
    @JoinColumn(name = "carteira_id")
    private List<Carteira> carteiras;
}