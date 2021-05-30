package br.com.zupacademy.mercadolivre.model.dtoderespostas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.mercadolivre.model.Caracteristica;
import br.com.zupacademy.mercadolivre.model.ImagemProduto;
import br.com.zupacademy.mercadolivre.model.Produto;

public class ProdutoResposta {

    private String nome;
    private BigDecimal valor;
    private Integer qtdDisponivel;
    private String descricao;
    private String nomeCategoria;
    private Set<CaracteristicaReposta> caracteristicas;
    private List<OpiniaoResposta> opinioes;
    private List<String> imagensUrls;
    private List<String> perguntas;
    private BigDecimal mediaNotas;
    private LocalDate data;

    public ProdutoResposta (Produto produtoModel){

        this.nome = produtoModel.getNome();
        this.valor = produtoModel.getValor();
        this.qtdDisponivel =  produtoModel.getQtdDisponivel();
        this.descricao = produtoModel.getDescricao();
        this.nomeCategoria = produtoModel.getCategoria().getNome();
        this.caracteristicas = produtoModel.getCaracteristica().stream().map(caracteristica -> new CaracteristicaReposta(caracteristica)).collect(Collectors.toSet());
        this.opinioes = produtoModel.getOpinioes().stream().map(opiniao -> new OpiniaoResposta(opiniao)).collect(Collectors.toList());
        this.imagensUrls = produtoModel.getImagensUrls().stream().map(image -> image.getLink()).collect(Collectors.toList());
        this.perguntas =  produtoModel.getPerguntas().stream().map(pergunta -> pergunta.getTitulo()).collect(Collectors.toList());
        this.mediaNotas = produtoModel.getPegarAMediaDasNotas();
        this.data = produtoModel.getData();
    }

    public String getNome() {
        return this.nome;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public Integer getQtdDisponivel() {
        return this.qtdDisponivel;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getNomeCategoria() {
        return this.nomeCategoria;
    }

    public List<OpiniaoResposta> getOpinioes() {
        return this.opinioes;
    }

    public List<String> getPerguntas() {
        return this.perguntas;
    }

    public BigDecimal getMediaNotas() {
        return this.mediaNotas;
    }

    public Set<CaracteristicaReposta> getCaracteristicas() {
        return this.caracteristicas;
    }

    public List<String> getImagensUrls() {
        return this.imagensUrls;
    }
  
    public LocalDate getData() {
        return this.data;
    }

}
