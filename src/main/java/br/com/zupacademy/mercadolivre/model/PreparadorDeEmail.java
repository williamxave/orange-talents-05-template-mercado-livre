package br.com.zupacademy.mercadolivre.model;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

public abstract class PreparadorDeEmail implements EnviadorDeEmailPrincipal{

    @Override
    public void confirmandoEmail(Pergunta pergunta, Usuario usuarioLogado){
        SimpleMailMessage sm = preparandoOEmailParaMandarAPergunta(pergunta, usuarioLogado);
        eviarOEmail(sm);
    }

    @Override
    public void confirmandoEmailParaCompra(Compra compra, Usuario usuariologadoCompra) {
        SimpleMailMessage sm = preparandoOEmailParaMandarACompra(compra, usuariologadoCompra);
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

    protected SimpleMailMessage preparandoOEmailParaMandarACompra(Compra compra, Usuario usuarioLogado){
        SimpleMailMessage preparandoCompra = new SimpleMailMessage();
        preparandoCompra.setTo(compra.getComprador().getLogin());
        preparandoCompra.setFrom(usuarioLogado.getLogin());
        preparandoCompra.setSubject("Compra enviada! Código da compra: " +compra.getCodigoDaCompra());
        preparandoCompra.setSentDate(new Date(System.currentTimeMillis()));
        preparandoCompra.setText(compra.toString());
        return preparandoCompra;
    } 
}
