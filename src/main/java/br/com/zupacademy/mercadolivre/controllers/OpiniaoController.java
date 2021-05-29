package br.com.zupacademy.mercadolivre.controllers;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Opiniao;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.OpiniaoDto;
import br.com.zupacademy.mercadolivre.repositories.OpiniaoRepository;
import br.com.zupacademy.mercadolivre.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    @Autowired
    public OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
   
    @PostMapping("/{id}/opiniao")
    public ResponseEntity<?> cadastraOpiniao( @PathVariable(value = "id") long idProduto,
                                  @RequestBody @Valid OpiniaoDto opiniaoDto, @AuthenticationPrincipal Usuario usuarioLogado){
      Optional<Produto>  podeTerUmProduto = produtoRepository.findById(idProduto);
      if(podeTerUmProduto.isPresent()){
        Opiniao opiniao = opiniaoDto.toModel(podeTerUmProduto,usuarioLogado);
        opiniaoRepository.save(opiniao);
        return ResponseEntity.ok().build();
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
  }
}
