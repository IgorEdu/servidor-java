package com.servidorjava.ServidorJava.repository;

import com.servidorjava.ServidorJava.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TaskRepository extends JpaRepository<Task, UUID> {
}
