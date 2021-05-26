package br.com.zupacademy.mercadolivre.exceptions.utils;

import java.util.ArrayList;
import java.util.List;

public class CapturandoErros extends ErroPadrao{

    private List<CampoDeMessagem> erros = new ArrayList<>();

    public CapturandoErros(Integer status, String mensagem, Long timeStamp) {
        super(status, mensagem, timeStamp);
    }

    public List<CampoDeMessagem> getErros() {
        return this.erros;
    }

    public void adicionandoErro(String nomeDoCampo, String mensagem){
        erros.add(new CampoDeMessagem(nomeDoCampo, mensagem));
    } 
}
