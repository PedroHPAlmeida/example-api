package com.example.demo.controller.form;

import com.example.demo.entity.Produto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter @Setter
public class ProdutoForm {

    @NotNull @NotEmpty @Length(min = 2, max = 255)
    private String produtoNome;
    @Positive
    private BigDecimal preco;

    public Produto converter(ModelMapper modelMapper) {
        Produto produto = new Produto();
        modelMapper.map(this, produto);
        return produto;
    }
}
