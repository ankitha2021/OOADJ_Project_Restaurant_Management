package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}