package br.com.zupacademy.mercadolivre.model.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.mercadolivre.model.Pergunta;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;

public class PerguntaDto {

    @NotBlank
    private String titulo;
    
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaDto(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Optional<Produto> poderTerUmProduto, Usuario usuarioLogado) {
        return new Pergunta(this.titulo, poderTerUmProduto.get(), usuarioLogado);
    }

}
