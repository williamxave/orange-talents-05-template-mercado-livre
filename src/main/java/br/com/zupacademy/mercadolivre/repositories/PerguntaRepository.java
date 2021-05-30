package br.com.zupacademy.mercadolivre.repositories;

import org.springframework.data.repository.CrudRepository;
import br.com.zupacademy.mercadolivre.model.Pergunta;

public interface PerguntaRepository extends CrudRepository<Pergunta, Long> {
    
}
