package com.justocraveiro.pcbuilding.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComputadorFormDto {
    @NotBlank
    private String nome;
    private String descricao;
    @NotNull
    private Long processadorId;
    @NotNull
    private Long ramId;
    @NotNull
    private Long armazenamentoId;
    @NotBlank
    private String categoria;

    public ComputadorFormDto() {
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

    public Long getProcessadorId() {
        return processadorId;
    }

    public void setProcessadorId(Long processadorId) {
        this.processadorId = processadorId;
    }

    public Long getRamId() {
        return ramId;
    }

    public void setRamId(Long ramId) {
        this.ramId = ramId;
    }

    public Long getArmazenamentoId() {
        return armazenamentoId;
    }

    public void setArmazenamentoId(Long armazenamentoId) {
        this.armazenamentoId = armazenamentoId;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
