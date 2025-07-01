package br.com.todolist.todolist_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.todolist_api.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
