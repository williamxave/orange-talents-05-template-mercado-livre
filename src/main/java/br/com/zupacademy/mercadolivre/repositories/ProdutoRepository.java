package br.com.zupacademy.mercadolivre.repositories;

import org.springframework.data.repository.CrudRepository;
import br.com.zupacademy.mercadolivre.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    
}
