package com.example.demo.controller.dto;

import com.example.demo.entity.Cliente;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

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

    public static Page<ClienteDto> converter(Page<Cliente> clientes){
        return clientes.map(ClienteDto::new); // O Spring oferece o método map para a classes do tipo 'Page', funcionando igual ao método da API de streams do Java
    }
}
