package br.com.zupacademy.mercadolivre.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.node.POJONode;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.exceptions.utils.CampoDeMessagem;
import br.com.zupacademy.mercadolivre.model.Compra;
import br.com.zupacademy.mercadolivre.model.EnviadorDeEmailPrincipal;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.CompraDto;
import br.com.zupacademy.mercadolivre.repositories.CompraRepository;
import br.com.zupacademy.mercadolivre.repositories.ProdutoRepository;

@RestController
@RequestMapping("compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnviadorDeEmailPrincipal enviadorDeEmailPrincipal;

    @PostMapping
    public ResponseEntity<?> fazCompra(@RequestBody @Valid CompraDto compraDto, @AuthenticationPrincipal Usuario comprador ){

        Optional<Produto> poderTerUmProduto =  produtoRepository.findById(compraDto.getidProduto());
        Integer verificaQuantidadeASerComprada = compraDto.getQuantidade();
        if(poderTerUmProduto.isEmpty()){
           return ResponseEntity.badRequest().build();
        }

        if(poderTerUmProduto.get().diminiuEstoque(verificaQuantidadeASerComprada)){
        Compra podemosTerUmaCompra = compraDto.toModel(poderTerUmProduto.get(), comprador);
        compraRepository.save(podemosTerUmaCompra);

        enviadorDeEmailPrincipal.confirmandoEmailParaCompra(podemosTerUmaCompra, comprador);
        return ResponseEntity.status(HttpStatus.MOVED_TEMPORARILY).body(podemosTerUmaCompra.getPegarUrl());
        }
        return ResponseEntity.badRequest().body(new CampoDeMessagem("Quantidade indisponivel" ,
                      "Estoque do produto indisponivel, temos " + poderTerUmProduto.get().getQtdDisponivel() + " produtos disponiveis!")); 
    }
}
