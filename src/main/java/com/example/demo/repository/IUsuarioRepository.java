package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método necessário na classe AutenticacaoService (porém não será usado diretamente, terá uma camada a mais, representada pela classe UsuarioService)
    Optional<Usuario> findByEmail(String email);

}
