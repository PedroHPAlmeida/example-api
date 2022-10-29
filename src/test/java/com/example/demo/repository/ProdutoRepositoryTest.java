package com.example.demo.repository;

import com.example.demo.entity.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProdutoRepositoryTest {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private TestEntityManager entityManager;

    public void salvarProduto() {
        Produto produto = new Produto();
        produto.setNome("Produto");
        produto.setPreco(new BigDecimal("10.0"));
        entityManager.persist(produto);
    }

    @Test
    void deveriaCarregarUmProdutoAoBuscarPeloSeuId() {
        salvarProduto();
        Optional<Produto> produto = produtoRepository.findById(1L);
        assertTrue(produto.isPresent());
        System.out.println("=================================================================================================\n" + produto.get());
        assertEquals("Produto", produto.get().getNome());
    }
}
