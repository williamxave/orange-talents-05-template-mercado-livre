package br.com.zupacademy.mercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDate dataDeCriacao = LocalDate.now();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Pergunta(String titulo,Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Pergunta(){
    }


    public Long getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public LocalDate getDataDeCriacao() {
        return this.dataDeCriacao;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }



    @Override
    public String toString() {
        return "{" +
            ", titulo='" + getTitulo() + "'" +
            ", dataDeCriacao='" + getDataDeCriacao() + "'" +
            ", produto='" + getProduto() + "'" +
            ", usuario='" + getUsuario() + "'" +
            "}";
    }

    
}
