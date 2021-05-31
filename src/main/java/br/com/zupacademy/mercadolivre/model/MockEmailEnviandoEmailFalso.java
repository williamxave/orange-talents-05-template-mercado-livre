package br.com.zupacademy.mercadolivre.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailEnviandoEmailFalso extends PreparadorDeEmail{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailEnviandoEmailFalso.class);

    @Override
    public void eviarOEmail(SimpleMailMessage mensagem) {
        LOG.info("Simulando o envio de email....");
        LOG.info(mensagem.toString());
        LOG.info("Email enviado!");
    }

}
