package br.com.zupacademy.mercadolivre.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.ProdutoDto;
import br.com.zupacademy.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repositories.UsuarioRepository;
import br.com.zupacademy.mercadolivre.validators.naoPermiteCadastrarNomeDeCaracteristicasIguaisValidator;

@RestController
@RequestMapping("/produto")
public class ProdutoContoller {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(new naoPermiteCadastrarNomeDeCaracteristicasIguaisValidator());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoDto produtoDto,@AuthenticationPrincipal Usuario usuarioLogado){
            Produto produto =  produtoDto.toModel(categoriaRepository, usuarioLogado);
            produtoRepository.save(produto);
            return ResponseEntity.ok().build();         
    }
    
}
