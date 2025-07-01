package br.com.todolist.todolist_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.todolist.todolist_api.model.Tarefa;
import br.com.todolist.todolist_api.model.Usuario;
import br.com.todolist.todolist_api.repository.TarefaRepository;
import br.com.todolist.todolist_api.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuarioLogado(Authentication authentication) {
        String email = authentication.getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    @GetMapping
    public List<Tarefa> getTarefasDoUsuario(Authentication authentication) {
        Usuario usuarioLogado = getUsuarioLogado(authentication);
        return tarefaRepository.findByUsuarioId(usuarioLogado.getId());
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa, Authentication authentication) {
        Usuario usuarioLogado = getUsuarioLogado(authentication);
        tarefa.setUsuario(usuarioLogado);
        Tarefa novaTarefa = tarefaRepository.save(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    @PutMapping("/{tarefaId}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long tarefaId, @RequestBody Tarefa detalhesTarefa, Authentication authentication) {
        Usuario usuarioLogado = getUsuarioLogado(authentication);

        return tarefaRepository.findById(tarefaId)
                .map(tarefa -> {
                    if (!tarefa.getUsuario().getId().equals(usuarioLogado.getId())) {
                        return new ResponseEntity<Tarefa>(HttpStatus.FORBIDDEN);
                    }
                    tarefa.setDescricao(detalhesTarefa.getDescricao());
                    tarefa.setConcluida(detalhesTarefa.isConcluida());
                    Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
                    return ResponseEntity.ok(tarefaAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{tarefaId}")
    public ResponseEntity<?> deletarTarefa(@PathVariable Long tarefaId, Authentication authentication) {
        Usuario usuarioLogado = getUsuarioLogado(authentication);

        return tarefaRepository.findById(tarefaId)
                .map(tarefa -> {
                    if (!tarefa.getUsuario().getId().equals(usuarioLogado.getId())) {
                        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                    }
                    tarefaRepository.delete(tarefa);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}