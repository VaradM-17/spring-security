package com.ss.spring_security.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.spring_security.dto.TodoDto;
import com.ss.spring_security.entity.Todo;
import com.ss.spring_security.service.TodoService;

import lombok.AllArgsConstructor;

@CrossOrigin("http://localhost:3000/")
@RequestMapping("/todos")
@AllArgsConstructor
@RestController
public class TodoController {

	private TodoService todoService;

	@PostMapping("/add-todo")
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		TodoDto todo = todoService.addTodo(todoDto);
		return new ResponseEntity<>(todo, HttpStatus.CREATED);
	}

	@GetMapping("/get-todo/{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id) {
		TodoDto todo = todoService.getTodo(id);
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}

	@GetMapping("/get-all-todos")
	public ResponseEntity<List<TodoDto>> getAllTodos() {
		List<TodoDto> todos = todoService.getAllTodos();
		return ResponseEntity.ok(todos);
	}

	@PutMapping("/update-todo/{id}")
	public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto) {
		TodoDto updatedTodo = todoService.updateTodo(todoId, todoDto);
		return ResponseEntity.ok(updatedTodo);
	}

	@DeleteMapping("/delete-todo/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
		todoService.deleteTodo(id);
		return ResponseEntity.ok("Todo Deleted");
	}

	@PatchMapping("/complete-todo/{id}")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
		TodoDto completeTodo = todoService.completeTodo(id);
		return ResponseEntity.ok(completeTodo);
	}
	
	@PatchMapping("/in-complete-todo/{id}")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id) {
		TodoDto incompleteTodo = todoService.inCompleteTodo(id);
		return ResponseEntity.ok(incompleteTodo);
	}
}
