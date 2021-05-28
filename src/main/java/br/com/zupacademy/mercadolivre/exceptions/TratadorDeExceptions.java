package br.com.zupacademy.mercadolivre.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mercadolivre.exceptions.utils.CapturandoErros;
import br.com.zupacademy.mercadolivre.exceptions.utils.ErroPadrao;

@RestControllerAdvice
public class TratadorDeExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException e , HttpServletRequest request){
        CapturandoErros erros = new CapturandoErros(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());

        for(FieldError x : e.getBindingResult().getFieldErrors()){
            erros.adicionandoErro(x.getField(), x.getDefaultMessage());
        }
        //Pega os erros globais, exceptions das anotações
        for(ObjectError x : e.getBindingResult().getGlobalErrors()){
            erros.adicionandoErro(x.getDefaultMessage(),x.getObjectName());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroPadrao> responseStatusException(ResponseStatusException e , HttpServletRequest request){
        ErroPadrao erros = new ErroPadrao(HttpStatus.FORBIDDEN.value(),
            "Você não tem autorização para isso, só pode alterar produtos que você é dono.",
            System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erros);
    }
}
