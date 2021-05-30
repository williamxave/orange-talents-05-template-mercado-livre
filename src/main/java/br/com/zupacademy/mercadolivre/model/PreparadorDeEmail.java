package br.com.zupacademy.mercadolivre.model;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

public abstract class PreparadorDeEmail implements EnviadorDeEmailPrincipal{

    @Override
    public void confirmandoEmail(Pergunta pergunta, Usuario usuarioLogado){
        SimpleMailMessage sm = preparandoOEmailParaMandarAPergunta(pergunta, usuarioLogado);
        eviarOEmail(sm);
    }

    protected SimpleMailMessage preparandoOEmailParaMandarAPergunta(Pergunta pergunta, Usuario usuarioLogado){
        SimpleMailMessage preparandoOEmail = new SimpleMailMessage();
        preparandoOEmail.setTo(pergunta.getUsuario().getLogin());
        preparandoOEmail.setFrom(usuarioLogado.getLogin());
        preparandoOEmail.setSubject("Pergunta enviada! Código da pergunta: " +pergunta.getId());
        preparandoOEmail.setSentDate(new Date(System.currentTimeMillis()));
        preparandoOEmail.setText(pergunta.toString());
        return preparandoOEmail;
    } 
}
