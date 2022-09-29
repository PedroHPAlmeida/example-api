package com.example.demo.controller.dto;

import com.example.demo.entity.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class ItemPedidoDto {

    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public static ItemPedidoDto converter(ItemPedido item, ModelMapper modelMapper) {
        ItemPedidoDto itemPedido = new ItemPedidoDto();
        modelMapper.map(itemPedido, item);
        return itemPedido;
    }

    public static List<ItemPedidoDto> converter(List<ItemPedido> itens, ModelMapper modelMapper) {
        return itens.stream().map(i -> ItemPedidoDto.converter(i, modelMapper)).collect(Collectors.toList());
    }
}
