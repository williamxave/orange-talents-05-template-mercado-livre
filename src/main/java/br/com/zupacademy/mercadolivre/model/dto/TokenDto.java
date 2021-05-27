package br.com.zupacademy.mercadolivre.model.dto;
public class TokenDto {

    private String token;
    private String string;

    public TokenDto(String token, String string) {
        this.token = token;
        this.string = string;
    }

    public String getToken() {
        return this.token;
    }

    public String getString() {
        return this.string;
    }

}
