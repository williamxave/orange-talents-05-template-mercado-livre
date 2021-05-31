package br.com.zupacademy.mercadolivre.model;

public enum FormaDePagamento {

    PagSeguro{

        @Override
        public String criadorDeUrl(String codigoDaCompra) {
            return "pagseguro.com?returnID=" +codigoDaCompra+ "&redirectUrl=Https://zupacademy/ot5/codigodecompra/sucesso";
            
        }},PayPal{

            @Override
            public String criadorDeUrl(String codigoDaCompra) {
                return "paypal.com?buyerId=" +codigoDaCompra+ "&redirectUrl=Https://zupacademy/ot5/codigodecompra/sucesso";
               
            }};

    public abstract String criadorDeUrl(String codigoDaCompra);
    
}
