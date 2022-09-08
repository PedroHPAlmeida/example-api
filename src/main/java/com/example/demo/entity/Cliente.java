package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "clientes") // Define como será o nome da tabela no Banco de Dados
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "data_nascimento") // define como será o nome do campo no Banco de Dados
    private LocalDate dataNascimento;

    private Character sexo;

}
