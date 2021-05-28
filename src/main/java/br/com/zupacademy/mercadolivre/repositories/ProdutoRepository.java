package br.com.zupacademy.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.mercadolivre.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
