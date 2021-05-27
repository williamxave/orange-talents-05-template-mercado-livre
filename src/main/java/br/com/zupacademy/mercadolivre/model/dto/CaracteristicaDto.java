package br.com.zupacademy.mercadolivre.model.dto;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mercadolivre.model.Caracteristica;
import br.com.zupacademy.mercadolivre.model.Produto;

public class CaracteristicaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Caracteristica toModel(Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }
    
}