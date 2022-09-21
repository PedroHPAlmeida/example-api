package com.example.demo.service;

import com.example.demo.controller.form.ProdutoFormUpdate;
import com.example.demo.entity.Produto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    public Page<Produto> listarTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto alterarPorId(Long id, ProdutoFormUpdate produtoAtualizado) {
        Produto produto = this.buscarPorId(id);
        return this.salvar(produtoAtualizado.converter(produto));
    }

    public void deletarPorId(Long id) {
        produtoRepository.delete(this.buscarPorId(id));
    }
}
