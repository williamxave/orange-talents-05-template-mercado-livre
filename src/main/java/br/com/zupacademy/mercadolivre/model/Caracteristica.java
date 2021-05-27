package br.com.zupacademy.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produtos;

    @Deprecated
    public Caracteristica() {
    }
    
    public Caracteristica(@NotBlank String nome,@NotBlank String descricao,@NotNull @Valid Produto produtos) {
        this.nome = nome;
        this.descricao = descricao;
        this.produtos = produtos;
    }
  
}
