package com.example.demo.config.security;

import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Profile(value = {"prd", "test"})
public class SecurityConfiguration {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/clientes").permitAll()
                .antMatchers(HttpMethod.GET, "/api/clientes/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/clientes/*").hasRole("NORMAL")
                .antMatchers(HttpMethod.DELETE, "/api/clientes/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/produtos/*").hasRole("NORMAL")
                .antMatchers(HttpMethod.GET, "/api/produtos").hasRole("NORMAL")
                .antMatchers(HttpMethod.POST, "/api/produtos").hasRole("NORMAL")
                .antMatchers(HttpMethod.PUT, "/api/produtos/*").hasRole("NORMAL")
                .antMatchers(HttpMethod.DELETE, "/api/produtos/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/pedidos/*").hasRole("NORMAL")
                .antMatchers(HttpMethod.GET, "/api/pedidos").hasRole("NORMAL")
                .antMatchers(HttpMethod.POST, "/api/pedidos").hasRole("NORMAL")
                .antMatchers(HttpMethod.DELETE, "/api/pedidos/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/**/api-docs").permitAll()
                .antMatchers("**/favicon.ico", "/css/**", "/images/**", "/js/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioService), UsernamePasswordAuthenticationFilter.class); // Faz com que o nosso filtro rode antes de autenticar o usu??rio

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(passwordEncoder());
    }

}
