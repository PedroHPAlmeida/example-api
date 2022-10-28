package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
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

import java.net.URI;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClienteService clienteService;
    private Cliente cliente;

    public void salvarCliente() {
        cliente = new Cliente();
        cliente.setNome("Cliente");
        cliente.setDataNascimento(LocalDate.now());
        cliente.setSexo("m");
        clienteService.salvar(cliente);
    }

    @Test
    public void deveriaDevolver201ComOsDadosDeClienteCorretos() throws Exception {
        URI uri = new URI("/api/clientes");
        String json = "{\"nome\": \"Cliente\", \"dataNascimento\": \"2000-01-01\", \"sexo\": \"m\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void deveriaRetornar404CasoOIdPassadoNaoExista() throws Exception{
        URI uri = new URI("/api/clientes/0");
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    @WithMockUser(value = "User", roles = "NORMAL") // Anotacao necessaria para testar endpoints que necessitam de autenticacao
    public void deveriaRetornar200CasoOIdExistaEORegistroTenhaSidoAlterado () throws Exception {
        salvarCliente();
        URI uri = new URI("/api/clientes/1");
        String json = "{\"nome\": \"Cliente Alterado\", \"dataNascimento\": \"1990-01-01\", \"sexo\": \"m\"}";

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
        salvarCliente();
        URI uri = new URI("/api/clientes/1");
        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(204));
    }
}
