package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;
    private Cliente cliente;

    @BeforeEach
    public void salvarCliente() {
        this.cliente = new Cliente();
        cliente.setNome("Cliente");
        cliente.setDataNascimento(LocalDate.now());
        cliente.setSexo("m");
        clienteService.salvar(cliente);
    }

    @Test
    public void deveriaCarregarUmClienteAoBuscarPeloSeuId() {
        Cliente cliente = clienteService.buscarPorId(1L);
        assertEquals("Cliente", cliente.getNome());
    }

    @Test
    public void deveriaLancarExceptionAoBuscarPorUmIdQueNaoExiste() {
        assertThrows(ResourceNotFoundException.class, () -> clienteService.buscarPorId(100L));
    }
}
