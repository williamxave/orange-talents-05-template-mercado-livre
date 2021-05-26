package br.com.zupacademy.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Perfil() {
    }

    public Perfil(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
    
}
