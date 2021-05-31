package br.com.zupacademy.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Compra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @CreationTimestamp
    private LocalDate dataDaCompra;

    @NotBlank
    private String codigoDaCompra;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    public Compra(){
    }

    public Compra(Integer quantidade, Produto produto, Usuario comprador,FormaDePagamento formaDePagamento) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.comprador = comprador;
        this.codigoDaCompra = UUID.randomUUID().toString();
        this.formaDePagamento = formaDePagamento;
    }

    public String getPegarUrl(){
        return this.formaDePagamento.criadorDeUrl(codigoDaCompra);
    }


    public Long getId() {
        return this.id;
    }


    public Integer getQuantidade() {
        return this.quantidade;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Usuario getComprador() {
        return this.comprador;
    }

    public LocalDate getDataDaCompra() {
        return this.dataDaCompra;
    }

    public String getCodigoDaCompra() {
        return this.codigoDaCompra;
    }

    public FormaDePagamento getFormaDePagamento() {
        return this.formaDePagamento;
    }

    public BigDecimal getValorTotal(Integer quantidade){
        return produto.getValor().multiply(BigDecimal.valueOf(quantidade.longValue())); 
       }
       
    @Override
    public String toString() {
        return "{" +
            ", quantidade='" + getQuantidade() + "'" +
            ", produto='" + getProduto() + "'" +
            ", comprador='" + getComprador() + "'" +
            ", dataDaCompra='" + getDataDaCompra() + "'" +
            ", codigoDaCompra='" + getCodigoDaCompra() + "'" +
            ", formaDePagamento='" + getFormaDePagamento() + "'" +
            ", valorTotal='" + getValorTotal(quantidade) + "'" +
            "}";
    }




}
