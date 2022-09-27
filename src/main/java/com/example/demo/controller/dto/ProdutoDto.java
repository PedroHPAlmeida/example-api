package com.example.demo.controller.dto;

import com.example.demo.entity.Produto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Getter @Setter
public class ProdutoDto {

    private String nome;
    private BigDecimal preco;

    public static ProdutoDto converter(Produto produto, ModelMapper modelMapper) {
        return modelMapper.map(produto, ProdutoDto.class);
    }

    public static Page<ProdutoDto> converter(Page<Produto> produtos, ModelMapper modelMapper) {
        return produtos.map(p -> converter(p, modelMapper));
    }
}
