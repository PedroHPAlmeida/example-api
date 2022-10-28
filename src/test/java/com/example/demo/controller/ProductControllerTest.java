package com.example.demo.controller;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoService produtoService;

    public void salvarProduto() {
        Produto produto = new Produto(1L, "Produto 1", new BigDecimal("10.00"));
        produtoService.salvar(produto);
    }

    @Test
    @WithMockUser(value = "User", roles = "NORMAL")
    public void deveriaDevolver201ComOsDadosDeCadastroCorretos() throws Exception {
        URI uri = new URI("/api/produtos");
        String json = "{ \"produtoNome\": \"Produto 1\", \"preco\": 10.0 }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    @WithMockUser(value = "User", roles = "NORMAL")
    public void deveriaDevolver200CasoOIdPassadoExista() throws Exception {
        salvarProduto();
        URI uri = new URI("/api/produtos/1");
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @WithMockUser(value = "User", roles = "NORMAL")
    public void deveriaDevolver404CasoOIdPassadoNaoexista() throws Exception {
        URI uri = new URI("/api/produtos/1");
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    @WithMockUser(value = "User", roles = "NORMAL")
    public void deveriaRetornar200CasoOIdExistaEORegistroTenhaSidoAlterado() throws Exception {
        salvarProduto();
        URI uri = new URI("/api/produtos/1");
        String json = "{ \"produtoNome\": \"Produto 1 Alterado\", \"preco\": 10.0 }";

        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    @WithMockUser(value = "Admin", roles = "ADMIN")
    public void deveriaRetornar204CasoOIdExistaEORegistroTenhaSidoDeletado() throws Exception {
        salvarProduto();
        URI uri = new URI("/api/produtos/1");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(204));
    }
}
