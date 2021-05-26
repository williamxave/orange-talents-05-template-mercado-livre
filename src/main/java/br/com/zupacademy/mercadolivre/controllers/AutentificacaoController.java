package br.com.zupacademy.mercadolivre.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.dto.TokenDto;
import br.com.zupacademy.mercadolivre.model.dto.UsuarioDto;
import br.com.zupacademy.mercadolivre.security.GeradorDeToken;

@RestController
@RequestMapping("/auth")
public class AutentificacaoController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GeradorDeToken geradorDeToken;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid UsuarioDto usuarioDto){

        UsernamePasswordAuthenticationToken dadosLogin = usuarioDto.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = geradorDeToken.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
