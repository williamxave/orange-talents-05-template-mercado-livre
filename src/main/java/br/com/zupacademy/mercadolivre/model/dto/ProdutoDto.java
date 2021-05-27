package br.com.zupacademy.mercadolivre.model.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zupacademy.mercadolivre.model.Categoria;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.mercadolivre.repositories.UsuarioRepository;
import br.com.zupacademy.mercadolivre.validators.IdExistente;

public class ProdutoDto {

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
    @IdExistente(domainClass = Categoria.class,fieldName = "id")
    private Long idCategoria;

    @IdExistente(domainClass = Usuario.class, fieldName = "id")
    private Long idAnunciante;
 
    @Valid
    @NotNull
    @Size(min = 3)
    private Set<CaracteristicaDto> caracteristicas = new HashSet<>();

    public ProdutoDto(@NotBlank String nome,@NotNull BigDecimal valor,@NotNull Integer qtdDisponivel,@NotBlank String descricao,
                            @NotNull Long idCategoria,
                                @NotNull @Size(min = 3) @Valid Set<CaracteristicaDto> caracteristicas, @NotNull Long idAnunciante) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario anunciante) {
       Optional<Categoria> categoria = categoriaRepository.findById(this.idCategoria);
        return new Produto(nome, valor, qtdDisponivel, descricao, categoria.get(),anunciante, caracteristicas);
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

    public Long getIdCategoria() {
        return this.idCategoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public void setQtdDisponivel(Integer qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
  
    public Set<CaracteristicaDto> getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(Set<CaracteristicaDto> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<String> buscarCaracteristicasIguais() {
        HashSet<String> nomesIguais  = new HashSet<>();
        HashSet<String> resultado  = new HashSet<>();
        for(CaracteristicaDto caracteristic : caracteristicas){
            String nome = caracteristic.getNome();
            if(!nomesIguais.add(nome)){
                resultado.add(nome);
            }
        }
        return resultado;
    }
}
