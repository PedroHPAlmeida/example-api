package com.example.demo.controller;

import com.example.demo.controller.dto.ProdutoDto;
import com.example.demo.controller.form.ProdutoForm;
import com.example.demo.controller.form.ProdutoFormUpdate;
import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
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
@RequestMapping(path = "/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoDto> salvar(@RequestBody @Valid ProdutoForm produtoForm, UriComponentsBuilder uriBuilder){
        Produto produto = produtoService.salvar(produtoForm.converter());
        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(new ProdutoDto(produto));
    }

    @GetMapping
    @Cacheable(value = "listaDeProdutos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProdutoDto> listarTodos(@PageableDefault(
            page = 0,
            size=5,
            sort= {"nome", "preco"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        return ProdutoDto.converter(produtoService.listarTodos(pageable));
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto buscarPorId(@PathVariable Long id){
        return new ProdutoDto(produtoService.buscarPorId(id));
    }

    @PutMapping(path = "/{id}")
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDto alterarPorId(@PathVariable Long id, @RequestBody @Valid ProdutoFormUpdate produtoFormUpdate) {
        return new ProdutoDto(produtoService.alterarPorId(id, produtoFormUpdate));
    }

    @DeleteMapping(path = "/{id}")
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        produtoService.deletarPorId(id);
    }
}
