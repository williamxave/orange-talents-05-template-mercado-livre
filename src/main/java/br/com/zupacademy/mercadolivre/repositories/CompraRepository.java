package br.com.zupacademy.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.mercadolivre.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
