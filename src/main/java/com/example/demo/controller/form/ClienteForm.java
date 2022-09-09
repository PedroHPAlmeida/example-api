package com.example.demo.controller.form;

import com.example.demo.entity.Cliente;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

// Exemplo de classe FORM que possui apenas os atributos definidos para que o usuário envie a API
// Esta class é igual a ClienteFormUpdate apenas para efeito de aprendizagem, entidades com mais atributos resultariam
// em classes DTO e FORM diferentes
@Getter
public class ClienteForm {

    @NotNull @NotEmpty @Length(min = 3, max = 255)
    private String nome;

    @Past @NotNull // @Past define que deve ser uma data passada
    private LocalDate dataNascimento;

    @Length(min = 1, max = 1)
    private String sexo;

    public Cliente converter(Cliente cliente){
        cliente.setNome(this.getNome());
        cliente.setDataNascimento(this.getDataNascimento());
        cliente.setSexo(this.getSexo());
        return cliente;
    }

    public Cliente converter(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setSexo(this.sexo);
        return cliente;
    }
}
