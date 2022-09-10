package com.example.demo.config.security;

import com.example.demo.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.jwt.expiration}")
    private String expiration;

    @Value("${api.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {

        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = recuperaDataExpiracao(hoje);

        return Jwts.builder()
                .setIssuer("Example API")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Date recuperaDataExpiracao(Date hoje) {
        return new Date(hoje.getTime() + Long.parseLong(expiration));
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}