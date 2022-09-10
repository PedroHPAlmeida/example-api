package com.example.demo.config.security;

import com.example.demo.entity.Usuario;
import com.example.demo.exceptions.AutenticacaoException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor // Gera o construtor com todos os atributos (necessário para fazer a injeção das dependências, visto que nessa classe não é possível usar o @Autowride)
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private static final String TIPO_AUTORIZACAO = "Bearer ";
    private static final String HEADER_AUTORIZACAO = "Authorization";

    private TokenService tokenService;
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recuperarToken(request);

        if(tokenService.isTokenValido(token)){
            this.autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) throws AutenticacaoException {
        Long idUsuario = tokenService.getIdUsuario(token);
        try {
            Usuario usuario = usuarioService.buscarPorId(idUsuario);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ResourceNotFoundException ex){
            throw new AutenticacaoException();
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTORIZACAO);

        if (ObjectUtils.isEmpty(token) || !token.startsWith(TIPO_AUTORIZACAO)) {
            return null;
        }
        return token.substring(7);
    }
}
