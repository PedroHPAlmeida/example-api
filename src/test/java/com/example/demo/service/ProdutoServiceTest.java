package com.example.demo.service;

import com.example.demo.entity.Produto;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    private Produto produto;

    public void salvarProduto() {
        produto = new Produto();
        produto.setNome("Produto");
        produto.setPreco(new BigDecimal("10.00"));
        produtoService.salvar(produto);
    }

    @Test
    public void deveriaRetornarUmProdutoAoBuscarPeloSeuId() {
        salvarProduto();
        Produto produtoBuscado = produtoService.buscarPorId(1L);
        assertEquals(produto.getNome(), produtoBuscado.getNome());
        assertEquals(produto.getPreco(), produtoBuscado.getPreco());
    }

    @Test
    public void deveriaDispararExceptionAoBuscarPorIdQueNaoExiste() {
        assertThrows(ResourceNotFoundException.class, () -> produtoService.buscarPorId(1L));
    }
}
