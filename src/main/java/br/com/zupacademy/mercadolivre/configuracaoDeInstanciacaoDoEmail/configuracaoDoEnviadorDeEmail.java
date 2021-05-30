package br.com.zupacademy.mercadolivre.configuracaoDeInstanciacaoDoEmail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.zupacademy.mercadolivre.model.EnviadorDeEmailPrincipal;
import br.com.zupacademy.mercadolivre.model.MockEmailEnviandoEmailFalso;

@Configuration
public class configuracaoDoEnviadorDeEmail {

    @Bean
    public  EnviadorDeEmailPrincipal enviar(){
        return new MockEmailEnviandoEmailFalso();
    }
    
}
