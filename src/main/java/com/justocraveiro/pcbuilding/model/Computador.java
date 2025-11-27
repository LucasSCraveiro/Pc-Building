package com.justocraveiro.pcbuilding.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
@Entity
public class Computador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    private String descricao;
    private String processador;
    private String ram;
    private String armazenamento;
    @NotNull
    private BigDecimal preco;
    private String categoria; 
    public Computador() {
    }
    public Computador(String nome, String descricao, String processador, String ram, String armazenamento, BigDecimal preco, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.processador = processador;
        this.ram = ram;
        this.armazenamento = armazenamento;
        this.preco = preco;
        this.categoria = categoria;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getArmazenamento() {
        return armazenamento;
    }
    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
