package com.example.demo.service;

import com.example.demo.entity.Pedido;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
    }

    public Page<Pedido> listarTodos(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public void deletarPorId(Long id) {
        pedidoRepository.delete(this.buscarPorId(id));
    }
}
