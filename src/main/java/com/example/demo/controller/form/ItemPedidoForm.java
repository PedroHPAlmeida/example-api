package com.example.demo.controller.form;

import com.example.demo.entity.ItemPedido;
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ItemPedidoForm {

    private Long idProduto;
    private Integer quantidade;

    public ItemPedido converter(ProdutoService produtoService, Pedido pedido) {
        ItemPedido itemPedido = new ItemPedido();
        Produto produto = produtoService.buscarPorId(this.idProduto);
        itemPedido.setProduto(produto);
        itemPedido.setPedido(pedido);
        itemPedido.setPrecoUnitario(produto.getPreco());
        itemPedido.setQuantidade(this.quantidade);
        return itemPedido;
    }

    public static List<ItemPedido> converter(List<ItemPedidoForm> itens, ProdutoService produtoService, Pedido pedido) {
        return itens.stream().map(item -> item.converter(produtoService, pedido)).collect(Collectors.toList());
    }

}
