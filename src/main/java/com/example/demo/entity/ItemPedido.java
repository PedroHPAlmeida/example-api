package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter
@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    private Integer quantidade;
    @ManyToOne @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    @ManyToOne @JoinColumn(name = "id_produto")
    private Produto produto;

}
