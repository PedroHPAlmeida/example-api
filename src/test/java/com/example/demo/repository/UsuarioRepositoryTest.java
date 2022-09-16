package com.example.demo.repository;

import com.example.demo.entity.Perfil;
import com.example.demo.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Notifica ao Spring que o BD configurado no projeto não deve ser substituído pelo BD em memória H2
@ActiveProfiles("test") // Força que o Profile ativo seja o 'test'
public class UsuarioRepositoryTest {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager; // EntityManager específico para testes fornecido pela anotação @DataJpaTest

    @Test
    void deveriaCarregarUmUsuarioAoBuscarPeloSeuEmail() {
        Perfil perfil = new Perfil();
        perfil.setNome("ROLE_NORMAL");
        entityManager.persist(perfil);

        Usuario user = new Usuario();
        user.setNome("User");
        user.setEmail("user@email.com");
        user.setSenha("12345");
        List<Perfil> perfis = List.of(perfil);
        user.setPerfis(perfis);
        entityManager.persist(user);

        Optional<Usuario> usuario = usuarioRepository.findByEmail("user@email.com");
        assertTrue(usuario.isPresent());
        assertEquals("User", usuario.get().getNome());
    }

    @Test
    void naoDeveriaCarregarUmUsuarioAoBuscarPorUmEmailNaoCadastrado() {
        Optional<Usuario> usuario = usuarioRepository.findByEmail("xyz@email.com");
        assertTrue(usuario.isEmpty());
    }
}
