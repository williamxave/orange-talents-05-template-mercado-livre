package br.com.zupacademy.mercadolivre.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.UsuarioDto;
import br.com.zupacademy.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    public UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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