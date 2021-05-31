package br.com.zupacademy.mercadolivre.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.mercadolivre.model.Compra;
import br.com.zupacademy.mercadolivre.model.FormaDePagamento;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.validators.IdExistente;
import br.com.zupacademy.mercadolivre.validators.ValidarFormaDePagamento;

public class CompraDto {

    @NotNull
    @Min(1)
    @Positive
    private Integer quantidade;

    @NotNull
    @IdExistente(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull
    @ValidarFormaDePagamento(enumClazz = FormaDePagamento.class)
    private String formaDePagamento;

    public CompraDto(Integer quantidade,String formaDePagamento, Long idProduto) {
        this.quantidade = quantidade;
        this.formaDePagamento = formaDePagamento;
        this.idProduto = idProduto;
    }

    public Compra toModel(Produto produto,Usuario comprador){
        return new Compra(quantidade, produto, comprador,FormaDePagamento.valueOf(this.formaDePagamento));
    }

    public Integer getQuantidade() {
        return this.quantidade;
    } 

    public Long getidProduto() {
        return this.idProduto;
    }

}
