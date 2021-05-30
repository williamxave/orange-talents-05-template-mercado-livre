package br.com.zupacademy.mercadolivre.model.dtoderespostas;

import br.com.zupacademy.mercadolivre.model.Opiniao;

public class OpiniaoResposta {

    private Integer nota;
    private String titulo;
    private String descricao;

    public OpiniaoResposta(Opiniao opiniao){
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Integer getNota() {
        return this.nota;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
