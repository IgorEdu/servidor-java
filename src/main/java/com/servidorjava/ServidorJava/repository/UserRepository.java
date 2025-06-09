package com.servidorjava.ServidorJava.repository;

import com.servidorjava.ServidorJava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
