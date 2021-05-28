package br.com.zupacademy.mercadolivre.validators;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.mercadolivre.model.dto.ProdutoDto;

@Component
public class NaoPermiteCadastrarNomeDeCaracteristicasIguaisValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        ProdutoDto request =  (ProdutoDto) target;
        Set<String> nomesIguais = request.buscarCaracteristicasIguais();
        if(!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Você não pode ter caracteristicas com nomes iguais");
        }
    } 
}
