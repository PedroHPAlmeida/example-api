package com.example.demo.controller.form;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Pedido;
import com.example.demo.service.ProdutoService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PedidoForm {

    private Long idCliente;
    private List<ItemPedidoForm> itens;

    public Pedido converter(Cliente cliente, ProdutoService produtoService) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(ItemPedidoForm.converter(itens, produtoService, pedido));
        return pedido;
    }
}
