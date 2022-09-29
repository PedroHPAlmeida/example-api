package com.example.demo.controller;

import com.example.demo.controller.dto.ClienteDto;
import com.example.demo.controller.form.ClienteForm;
import com.example.demo.controller.form.ClienteFormUpdate;
import com.example.demo.entity.Cliente;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @CacheEvict(value = "listaDeClientes", allEntries = true) // Invalida/Limpa o cache definido no parâmetro 'value'
    @ResponseStatus(HttpStatus.CREATED) // Para o correto funcionamento (geração do Swagger) do Spring Doc devemos anotar todos os métodos com @ResponseStatus, mesmo que pareça redundante
    public ResponseEntity<ClienteDto> salvar(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteService.salvar(clienteForm.converter());
        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri(); // Retorna no cabeçalho de resposta da requisição a URL para buscar o recurso que acabou de ser criado
        return ResponseEntity
                .created(uri)
                .body(ClienteDto.converter(cliente, modelMapper));
    }

    @GetMapping
    @Cacheable(value = "listaDeClientes") // Parâmetro 'value' servirá como um id para o cache, para que ele seja diferenciado dos demais métodos que usarem cache
    @ResponseStatus(HttpStatus.OK)
    public Page<ClienteDto> listarTodos(
            @PageableDefault(
                    page = 0, // O padrão já é a página 0 (ZERO)
                    size = 5,
                    sort = {"sexo", "nome"}, // A lista será ordenada por padrão por cada um dos atributos do array, caso o cliente da API não envie nenhum parâmetro
                    direction = Sort.Direction.ASC) Pageable paginacao){ // O parâmetro Pageable não deve ser anotado com @ResquestParam(required = false), o campo já é opcional por padrão, caso adicione a anotação causará um erro se o usuário não enviar parâmetros de ordenação (page, size, sort)
        return ClienteDto.converter(clienteService.listarTodos(paginacao), modelMapper);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ClienteDto.converter(clienteService.buscarPorId(id), modelMapper);
    }

    @PutMapping(path = "/{id}")
    @CacheEvict(value = "listaDeClientes", allEntries = true)
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto alterarPorId(@PathVariable Long id, @RequestBody @Valid ClienteFormUpdate clienteFormUpdate){
        Cliente cliente = clienteService.buscarPorId(id);
        modelMapper.map(clienteFormUpdate, cliente);
        return ClienteDto.converter(clienteService.salvar(cliente), modelMapper);
    }

    @DeleteMapping(path = "/{id}")
    @CacheEvict(value = "listaDeClientes", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id){
        clienteService.deletarPorId(id);
    }
}
