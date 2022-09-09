package com.example.demo.controller;

import com.example.demo.controller.dto.ClienteDto;
import com.example.demo.controller.form.ClienteForm;
import com.example.demo.controller.form.ClienteFormUpdate;
import com.example.demo.entity.Cliente;
import com.example.demo.exceptions.ClienteNotFoundException;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @CacheEvict(value = "listaDeClientes", allEntries = true) // Invalida/Limpa o cache definido no parâmetro 'value'
    public ResponseEntity<ClienteDto> salvar(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteService.salvar(clienteForm.converter());
        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri(); // Retorna no cabeçalho de resposta da requisição a URL para buscar o recurso que acabou de ser criado
        return ResponseEntity
                .created(uri)
                .body(new ClienteDto(cliente));
    }

    @GetMapping
    @Cacheable(value = "listaDeClientes") // Parâmetro 'value' servirá como um id para o cache, para que ele seja diferenciado dos demais métodos que usarem cache
    public List<ClienteDto> listarTodos(){
        return ClienteDto.converter(clienteService.listarTodos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Long id){
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(new ClienteDto(cliente));
        } catch (ClienteNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    @CacheEvict(value = "listaDeClientes", allEntries = true)
    public ResponseEntity<ClienteDto> alterarPorId(@PathVariable Long id, @RequestBody @Valid ClienteFormUpdate clienteFormUpdate){
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            Cliente clienteAtualizado = clienteFormUpdate.converter(cliente);
            clienteService.salvar(clienteAtualizado);
            return ResponseEntity.ok(new ClienteDto(clienteAtualizado));
        } catch (ClienteNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @CacheEvict(value = "listaDeClientes", allEntries = true)
    public ResponseEntity<?> deletarPorId(@PathVariable Long id){
        try {
            clienteService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
