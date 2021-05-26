package br.com.zupacademy.mercadolivre.model.dto;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mercadolivre.model.Categoria;
import br.com.zupacademy.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.mercadolivre.validators.CampoUnico;
import br.com.zupacademy.mercadolivre.validators.IdExistente;

public class CategoriaDto {

    @NotBlank
    @CampoUnico(domainClass = Categoria.class, fieldName = "nome")
    public String nome;

    @IdExistente(domainClass = Categoria.class, fieldName = "id")
    public Long idCategoriaMae;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoriaMae() {
        return this.idCategoriaMae;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository){
        if (this.getIdCategoriaMae() != null){
           Categoria categoriaMae = categoriaRepository.findById(this.getIdCategoriaMae()).get();
           return new Categoria(this.nome, categoriaMae);
        }   
        return new Categoria(this.nome);
    }
 
}
