package br.com.zupacademy.mercadolivre.exceptions.utils;

public class ErroPadrao {

    private Integer status;
    private String mensagem;
    private Long timeStamp;

    public ErroPadrao(Integer status, String mensagem, Long timeStamp) {
        this.status = status;
        this.mensagem = mensagem;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return this.status;
    }
    public String getMensagem() {
        return this.mensagem;
    }
    public Long getTimeStamp() {
        return this.timeStamp;
    }

}
