package br.com.zupacademy.mercadolivre.model;

import org.springframework.mail.SimpleMailMessage;

public interface EnviadorDeEmailPrincipal {

    void confirmandoEmail(Pergunta pergunta,Usuario usuariologado);

    void eviarOEmail(SimpleMailMessage mensagem);
}
