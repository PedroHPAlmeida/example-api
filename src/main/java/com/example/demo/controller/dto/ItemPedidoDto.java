package com.example.demo.controller.dto;

import com.example.demo.entity.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ItemPedidoDto {

    private String produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public ItemPedidoDto(ItemPedido item) {
        this.produto = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
    }

    public static List<ItemPedidoDto> converter(List<ItemPedido> itens) {
        return itens.stream().map(ItemPedidoDto::new).collect(Collectors.toList());
    }
}
