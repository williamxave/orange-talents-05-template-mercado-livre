package br.com.zupacademy.mercadolivre.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.mercadolivre.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long>{

    Optional<Usuario> findByLogin(String login);

}
