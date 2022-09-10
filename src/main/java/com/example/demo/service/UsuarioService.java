package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario buscarPorId(Long id) throws ResourceNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        }
        throw new ResourceNotFoundException("Usuário não encontrado");
    }
}
