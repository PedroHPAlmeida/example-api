package com.example.demo.controller.dto;

import com.example.demo.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class ClienteDtoTest {

    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);
    }

    @Test
    public void deveriaRetornarUmObjetoClienteDtoComOsValoresDosAtributosIguaisAoCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente");
        cliente.setDataNascimento(LocalDate.now());
        cliente.setSexo("m");
        ClienteDto clienteDto = ClienteDto.converter(cliente, modelMapper);
        assertEquals(clienteDto.getNome(), cliente.getNome());
        assertEquals(clienteDto.getDataNascimento(), cliente.getDataNascimento());
        assertEquals(clienteDto.getSexo(), cliente.getSexo());
    }
}
