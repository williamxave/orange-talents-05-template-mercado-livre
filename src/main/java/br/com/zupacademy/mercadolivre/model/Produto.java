package br.com.zupacademy.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.mercadolivre.model.dto.CaracteristicaDto;
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private Integer qtdDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull 
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @Valid
    private Usuario anunciante;

    @Valid
    @NotNull
    @Size(min = 3)
    @OneToMany(mappedBy = "produtos", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristica =  new HashSet<>();

    //Merge quando atualizar um produto vai atualizar as imagens junto
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagensUrls = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes =  new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas =  new ArrayList<>();

    @PastOrPresent
    private LocalDate data = LocalDate.now();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome,@NotNull @Positive BigDecimal valor,
                         @NotNull @Min(value = 0) Integer qtdDisponivel,@NotBlank @Length(max = 1000) String descricao,@NotNull @Valid Categoria categoria,
                                             @NotNull @Valid Usuario anunciante,@NotNull @Size(min = 3) @Valid Collection<CaracteristicaDto> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.anunciante = anunciante;
        this.caracteristica.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
    }

    public void associaImagemAoProduto(List<String> links){
         List<ImagemProduto> imagemProdutos =  links.stream()
                                .map(link -> new ImagemProduto(this,link))
                                .collect(Collectors.toList());
            this.imagensUrls.addAll(imagemProdutos);
    }

    //Méttodo auxiliar para verificar se o usuário que quer adicionar uma imagem ao produto é o dono da imagem
    public boolean perteceAoUsuarioLogado(Long supostoDonoDaImagem){
        return this.anunciante.getId() == supostoDonoDaImagem;
    }


    public BigDecimal getPegarAMediaDasNotas() {
        List<Integer> notas = this.opinioes.stream().map(opini -> opini.getNota()).collect(Collectors.toList());
        Integer totalDeNotas = notas.stream().reduce(0, (subtotal, element) -> subtotal + element);
        return BigDecimal.valueOf(totalDeNotas).divide(BigDecimal.valueOf(getTotalDeOpinioes()));
    }

    public Integer getTotalDeOpinioes() {
        return this.opinioes.size();
    }

    public boolean verificaSeTemProdutoNoEstoque(Integer quantidadeASerComprada){
        return this.qtdDisponivel >= quantidadeASerComprada;
    }

    public boolean diminiuEstoque(Integer quantidadeASerComprada){
        if(verificaSeTemProdutoNoEstoque(quantidadeASerComprada)){
            this.qtdDisponivel -= quantidadeASerComprada;
            return true;
        }
        return false;
    }

   
    public Long getId() {
        return this.id;
    }

    public Usuario getAnunciante() {
        return this.anunciante;
    }

    public List<Opiniao> getOpinioes() {
        return this.opinioes;
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

    public Categoria getCategoria() {
        return this.categoria;
    }

    public Set<Caracteristica> getCaracteristica() {
        return this.caracteristica;
    }

    public List<ImagemProduto> getImagensUrls() {
        return this.imagensUrls;
    }


    public List<Pergunta> getPerguntas() {
        return this.perguntas;
    }

    public LocalDate getData() {
        return this.data;
    }
   

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", valor='" +getValor() + "'" +
            ", anunciante='" + getAnunciante() + "'" +
            "}";
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Produto)) {
            return false;
        }
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome)  && Objects.equals(anunciante, produto.anunciante);
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    

}
