package com.example.demo.controller.dto;

import com.example.demo.entity.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

// Exemplo de classe DTO que possui apenas parte dos atributos da entidade JPA
@Getter @Setter
public class ClienteDto {

    private String nome;
    private LocalDate dataNascimento;
    private String sexo;

    public static ClienteDto converter(Cliente cliente, ModelMapper modelMapper) {
        return modelMapper.map(cliente, ClienteDto.class);
    }

    public static Page<ClienteDto> converter(Page<Cliente> clientes, ModelMapper modelMapper){
        return clientes.map(c -> ClienteDto.converter(c, modelMapper)); // O Spring oferece o método map para a classes do tipo 'Page', funcionando igual ao método da API de streams do Java
    }
}
