package br.com.zupacademy.mercadolivre.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.EnviadorDeEmailPrincipal;
import br.com.zupacademy.mercadolivre.model.Pergunta;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.PerguntaDto;
import br.com.zupacademy.mercadolivre.repositories.PerguntaRepository;
import br.com.zupacademy.mercadolivre.repositories.ProdutoRepository;

@RestController
@RequestMapping("/{id}/pergunta")
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private EnviadorDeEmailPrincipal enviadorDeEmailPrincipal;

    @PostMapping
    public ResponseEntity<?> cadastraPerguntaAoProduto(@RequestBody @Valid PerguntaDto perguntaDto,
                                                @PathVariable(name = "id") Long idProduto,@AuthenticationPrincipal Usuario usuarioLogado){
       Optional<Produto> poderTerUmProduto = produtoRepository.findById(idProduto);
       Pergunta novaPergunta = perguntaDto.toModel(poderTerUmProduto,usuarioLogado);                   
       perguntaRepository.save(novaPergunta);

       enviadorDeEmailPrincipal.confirmandoEmail(novaPergunta,usuarioLogado);
        return ResponseEntity.ok().build();
    } 
}
