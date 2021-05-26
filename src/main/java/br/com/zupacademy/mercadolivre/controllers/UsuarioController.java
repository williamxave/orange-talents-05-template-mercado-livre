package br.com.zupacademy.mercadolivre.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.UsuarioDto;
import br.com.zupacademy.mercadolivre.repositories.UsuarioRepository;
import br.com.zupacademy.mercadolivre.validators.NaoDeixaRepitirEmail;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private NaoDeixaRepitirEmail naodeixaRepitirEmail;
    public UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(naodeixaRepitirEmail);
    }


    @PostMapping
    public ResponseEntity<?> cadastraUsuario(@RequestBody @Valid UsuarioDto usuarioDto){

        if(usuarioDto != null){
            Usuario usuario =  usuarioDto.toModel();
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}