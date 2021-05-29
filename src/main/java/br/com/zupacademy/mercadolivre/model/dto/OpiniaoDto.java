package br.com.zupacademy.mercadolivre.model.dto;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.mercadolivre.model.Opiniao;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;

public class OpiniaoDto {

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoDto(@NotNull @Min(value = 1) @Max(value = 5) Integer nota, @NotBlank String titulo,
                             @Size(max = 500) @NotBlank String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Optional<Produto> podeTerUmProduto, Usuario usuarioLogado) {
        return new Opiniao(this.nota, this.titulo,this.descricao, usuarioLogado, podeTerUmProduto.get());
    }

}
