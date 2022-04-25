package com.smartcash.engine.models.domain;

import lombok.Data;

import javax.persistence.*;

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

    private String confirmSenha;

    @ManyToOne
    @JoinColumn(name = "carteira_id")
    private Carteira carteira;
}