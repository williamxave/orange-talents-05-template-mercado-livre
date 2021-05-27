package br.com.zupacademy.mercadolivre.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.UsuarioDto;
import br.com.zupacademy.mercadolivre.repositories.UsuarioRepository;

@Component
public class NaoDeixaRepitirEmail implements Validator{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        UsuarioDto request = (UsuarioDto) target;
        Optional<Usuario> usuario = usuarioRepository.findByLogin(request.getLogin());
        if(usuario.isPresent()){
            errors.reject("email",null, "E-mail já cadastrado!");
        }
        
    }
    
}
