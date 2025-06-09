package com.servidorjava.ServidorJava.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//classe responsavel por desabilitar a verificaçao das rotas no spring secutiry
//no futuro se for necessario, implementar autenticaçao
//foi necessário criar essa classe para poder criar um usuário com senha cryptografada
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
