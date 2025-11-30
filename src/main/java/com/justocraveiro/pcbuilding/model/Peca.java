package com.justocraveiro.pcbuilding.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoPeca tipo;

    private BigDecimal preco;

    public Peca() {
    }

    public Peca(String nome, TipoPeca tipo, BigDecimal preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPeca getTipo() {
        return tipo;
    }

    public void setTipo(TipoPeca tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
