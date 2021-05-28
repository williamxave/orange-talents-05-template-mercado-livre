package br.com.zupacademy.mercadolivre.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @NotNull
    @Valid
    private Produto produto;

    @Deprecated
    public ImagemProduto(){
    }

    @URL
    @NotBlank
    private String link;

    public ImagemProduto(@NotNull @Valid Produto produto,@NotBlank @URL String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ImagemProduto)) {
            return false;
        }
        ImagemProduto imagemProduto = (ImagemProduto) o;
        return  Objects.equals(produto, imagemProduto.produto) && Objects.equals(link, imagemProduto.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }

}
