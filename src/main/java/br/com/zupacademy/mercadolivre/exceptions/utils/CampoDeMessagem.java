package br.com.zupacademy.mercadolivre.exceptions.utils;

public class CampoDeMessagem {

    private String nomeDoCampo;

    private String mensagem;

    public CampoDeMessagem(String nomeDoCampo, String mensagem) {
        this.nomeDoCampo = nomeDoCampo;
        this.mensagem = mensagem;
    }

    public String getNomeDoCampo() {
        return this.nomeDoCampo;
    }

    public String getMensagem() {
        return this.mensagem;
    }
    
}
