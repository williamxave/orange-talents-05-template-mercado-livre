package br.com.zupacademy.mercadolivre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.dtoderespostas.ProdutoResposta;
import br.com.zupacademy.mercadolivre.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class PaginaDeDetalheController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResposta> buscaDadosProdutoPagina(@PathVariable(value = "id") Long idProduto){
       Produto produto =  produtoRepository.findById(idProduto).get();

       return  ResponseEntity.ok().body(new ProdutoResposta(produto));
    }
}
