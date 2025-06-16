package com.servidorjava.ServidorJava.controller;

import com.servidorjava.ServidorJava.dto.UserDTO;
import com.servidorjava.ServidorJava.model.User;
import com.servidorjava.ServidorJava.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserDTO user) {
        User newUser = user.mapping();
        service.save(newUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable UUID id) {
        System.out.println("ID recebido: " + id);
        User user = service.get(id);

        return (user != null) ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
