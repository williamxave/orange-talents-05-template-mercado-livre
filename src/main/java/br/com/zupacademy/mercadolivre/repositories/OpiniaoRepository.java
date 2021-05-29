package br.com.zupacademy.mercadolivre.repositories;

import org.springframework.data.repository.CrudRepository;
import br.com.zupacademy.mercadolivre.model.Opiniao;

public interface OpiniaoRepository extends CrudRepository<Opiniao, Long> {
    
}
