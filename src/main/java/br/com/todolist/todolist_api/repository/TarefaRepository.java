package br.com.todolist.todolist_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.todolist_api.model.Tarefa;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuarioId(Long usuarioId);
}
