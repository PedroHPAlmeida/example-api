package com.example.demo.controller.dto;

import com.example.demo.entity.Cliente;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// Exemplo de classe DTO que possui apenas parte dos atributos da entidade JPA
@Getter
public class ClienteDto {

    private String nome;
    private LocalDate dataNascimento;
    private String sexo;

    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.dataNascimento = cliente.getDataNascimento();
        this.sexo = cliente.getSexo();
    }

    public static List<ClienteDto> converter(List<Cliente> clientes){
        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }
}
