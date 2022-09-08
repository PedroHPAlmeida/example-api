package com.example.demo.controller;

import com.example.demo.controller.dto.ClienteDto;
import com.example.demo.controller.form.ClienteForm;
import com.example.demo.controller.form.ClienteFormUpdate;
import com.example.demo.entity.Cliente;
import com.example.demo.exceptions.ClienteNotFoundException;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> salvar(@RequestBody ClienteForm clienteForm, UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteService.salvar(clienteForm.converter());
        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri(); // Retorna no cabeçalho de resposta da requisição a URL para buscar o recurso que acabou de ser criado
        return ResponseEntity
                .created(uri)
                .body(new ClienteDto(cliente));
    }

    @GetMapping
    public List<ClienteDto> listarTodos(){
        return ClienteDto.converter(clienteService.listarTodos());
    }
}
