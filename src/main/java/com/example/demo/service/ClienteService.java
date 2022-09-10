package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){ // O método de salvar serve também para atualizar registros
        return clienteRepository.save(cliente);
    }

    public Page<Cliente> listarTodos(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Cliente buscarPorId(Long id) throws ResourceNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return cliente.get();
        }
        throw new ResourceNotFoundException("Cliente não encontrado!");
    }

    public void deletarPorId(Long id) throws ResourceNotFoundException {
        Cliente cliente = this.buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}
