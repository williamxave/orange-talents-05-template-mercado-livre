package br.com.zupacademy.mercadolivre.model.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

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

    @JsonCreator(mode = Mode.PROPERTIES)
    public CategoriaDto(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoriaMae() {
        return this.idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository){
        if(this.idCategoriaMae != null){
           Optional<Categoria> categoriaMae = categoriaRepository.findById(this.getIdCategoriaMae());
           if(categoriaMae.isPresent()){
                return new Categoria(this.nome, categoriaMae.get());
           }
        }
        return new Categoria(this.nome);
    }
}
