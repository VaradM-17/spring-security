package com.ss.spring_security.service;

import java.util.List;

import com.ss.spring_security.dto.TodoDto;

public interface TodoService {

	// create
	TodoDto addTodo(TodoDto TodoDto);

	// get by id
	TodoDto getTodo(Long id);

	// get all
	List<TodoDto> getAllTodos();
	
	// update
	TodoDto updateTodo(Long id ,TodoDto todoDto);
	
	// delete
	void deleteTodo(Long id);
	
	// status complete
	TodoDto completeTodo(Long id);
	
	// status incomplete
	TodoDto inCompleteTodo(Long id);
}
