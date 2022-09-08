package com.example.demo.controller.form;

import com.example.demo.entity.Cliente;
import lombok.Getter;

import java.time.LocalDate;

// Exemplo de classe FORM que possui apenas os atributos definidos para que o usuário envie a API
@Getter
public class ClienteFormUpdate {

    private String nome;
    private LocalDate dataNascimento;
    private Character sexo;

    public Cliente converter(Cliente cliente){
        cliente.setNome(this.getNome());
        cliente.setDataNascimento(this.getDataNascimento());
        cliente.setSexo(this.getSexo());
        return cliente;
    }
}
