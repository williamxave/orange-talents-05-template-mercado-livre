package br.com.zupacademy.mercadolivre.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.mercadolivre.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    Optional<Categoria> findById(Long id);
    
}
