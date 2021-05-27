package br.com.zupacademy.mercadolivre.repositories;

import org.springframework.data.repository.CrudRepository;
import br.com.zupacademy.mercadolivre.model.Caracteristica;

public interface CaracteristicaRepository extends CrudRepository<Caracteristica, Long> {
    
}
