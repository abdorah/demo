package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Demo;

public interface DemoRepository extends JpaRepository<Demo, UUID> {
    
}
