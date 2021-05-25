package br.com.zupacademy.mercadolivre.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.mercadolivre.model.Usuario;

public class UsuarioDto {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioDto( @NotBlank @Email String login,  @NotBlank @Length(min = 6)String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login, this.senha);
    }
}
