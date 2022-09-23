package com.example.demo.controller.dto;

import com.example.demo.entity.Pedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class PedidoDto {

    private String cliente;
    private LocalDate data;
    private List<ItemPedidoDto> itens;

    public PedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.cliente = pedido.getCliente().getNome();
        this.itens = ItemPedidoDto.converter(pedido.getItens());
    }

    public static Page<PedidoDto> converter(Page<Pedido> pedidos) {
        return pedidos.map(PedidoDto::new);
    }
}
