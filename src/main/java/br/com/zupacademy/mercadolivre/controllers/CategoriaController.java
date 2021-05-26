package br.com.zupacademy.mercadolivre.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Categoria;
import br.com.zupacademy.mercadolivre.model.dto.CategoriaDto;
import br.com.zupacademy.mercadolivre.repositories.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @PostMapping
    public ResponseEntity<?> cadastraCategoria(@RequestBody @Valid CategoriaDto categoriaDto){
        Categoria categoria = categoriaDto.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().build();
    }
}
