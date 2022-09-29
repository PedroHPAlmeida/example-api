package com.example.demo.controller.dto;

import com.example.demo.entity.Pedido;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class PedidoDto {

    private String clienteNome;
    private LocalDate data;
    private List<ItemPedidoDto> itens;

    public static PedidoDto converter(Pedido pedido, ModelMapper modelMapper) {
        return modelMapper.map(pedido, PedidoDto.class);
    }

    public static Page<PedidoDto> converter(Page<Pedido> pedidos, ModelMapper modelMapper) {
        return pedidos.map(p -> PedidoDto.converter(p, modelMapper));
    }
}
