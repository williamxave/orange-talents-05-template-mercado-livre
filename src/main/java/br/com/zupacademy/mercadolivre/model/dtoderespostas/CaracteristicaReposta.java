package br.com.zupacademy.mercadolivre.model.dtoderespostas;

import br.com.zupacademy.mercadolivre.model.Caracteristica;

public class CaracteristicaReposta {

    private String nome;

    private String descricao;

    public CaracteristicaReposta(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    
}
