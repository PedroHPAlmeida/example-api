package com.example.demo.controller.dto;

import com.example.demo.entity.Produto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Getter @Setter
public class ProdutoDto {

    private String nome;
    private BigDecimal preco;

    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }

    public static Page<ProdutoDto> converter(Page<Produto> produtos) {
        return produtos.map(ProdutoDto::new);
    }
}
