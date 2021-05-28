package br.com.zupacademy.mercadolivre.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.UploaderFake;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.ImagemProdutoDto;
import br.com.zupacademy.mercadolivre.model.dto.ProdutoDto;
import br.com.zupacademy.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repositories.UsuarioRepository;
import br.com.zupacademy.mercadolivre.validators.NaoPermiteCadastrarNomeDeCaracteristicasIguaisValidator;
@RestController
@RequestMapping("/produto")
public class ProdutoContoller {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @InitBinder(value = "produtoDto")
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(new NaoPermiteCadastrarNomeDeCaracteristicasIguaisValidator());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoDto produtoDto,@AuthenticationPrincipal Usuario usuarioLogado){
            Produto produto =  produtoDto.toModel(categoriaRepository, usuarioLogado);
            produtoRepository.save(produto);
            return ResponseEntity.ok().build();         
    }


    // Aqui não usamos @RequestBody porque o dado que o cliente envia em geral é multi-form-data e não json e o spring já tem suporte a essas midias
    @PostMapping("/{id}/imagens")
    public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id,@Valid ImagemProdutoDto imagemProdutoDto,@AuthenticationPrincipal Usuario usuarioLogado){

            Produto podeTerProduto = produtoRepository.findById(id).get();

            //Verifica se o produto pertence ao usuário logado
            if(!podeTerProduto.perteceAoUsuarioLogado(usuarioLogado.getId())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
                List<String> links = uploaderFake.envia(imagemProdutoDto.getImagens());
                podeTerProduto.associaImagemAoProduto(links);
                produtoRepository.save(podeTerProduto);
                return ResponseEntity.ok().build();
    }
}
