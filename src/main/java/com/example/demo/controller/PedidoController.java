package com.example.demo.controller;

import com.example.demo.controller.dto.PedidoDto;
import com.example.demo.controller.form.PedidoForm;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Pedido;
import com.example.demo.service.ClienteService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProdutoService;
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
@RequestMapping(path = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @CacheEvict(value = "listaDePedidos", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PedidoDto> salvar(@RequestBody @Valid PedidoForm pedidoForm, UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteService.buscarPorId(pedidoForm.getIdCliente());
        Pedido pedido = pedidoService.salvar(pedidoForm.converter(cliente, produtoService));
        URI uri = uriBuilder.path("/api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(PedidoDto.converter(pedido, modelMapper));
    }

    @GetMapping
    @Cacheable(value = "listaDePedidos")
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoDto> listarTodos(@PageableDefault(
            page = 0,
            size=5,
            sort= "data",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return PedidoDto.converter(pedidoService.listarTodos(pageable), modelMapper);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoDto buscarPorId(@PathVariable Long id){
        return PedidoDto.converter(pedidoService.buscarPorId(id), modelMapper);
    }

    @DeleteMapping(path = "/{id}")
    @CacheEvict(value = "listaDePedidos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        pedidoService.deletarPorId(id);
    }
}
