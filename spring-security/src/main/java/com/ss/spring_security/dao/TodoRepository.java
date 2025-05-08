package com.ss.spring_security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.spring_security.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
