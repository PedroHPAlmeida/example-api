package com.example.demo.controller.form;

import com.example.demo.entity.Produto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter @Setter
public class ProdutoForm {

    @NotNull @NotEmpty @Length(min = 2, max = 255)
    private String nome;
    @Positive
    private BigDecimal preco;

    public Produto converter() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(new BigDecimal(preco.toString()));
        return produto;
    }
}
