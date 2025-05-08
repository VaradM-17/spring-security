package com.ss.spring_security.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ss.spring_security.dao.TodoRepository;
import com.ss.spring_security.dto.TodoDto;
import com.ss.spring_security.entity.Todo;
import com.ss.spring_security.exception.ResourceNotFoundException;
import com.ss.spring_security.service.TodoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

	private TodoRepository todorepository;
	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo savedTodo = todorepository.save(todo);
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		Todo todo = todorepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found by id : " + id));
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todorepository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(Long id, TodoDto todoDto) {
		Todo todo = todorepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found by id : " + id));
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setStatus(todoDto.isStatus());

		Todo todoUpdated = todorepository.save(todo);

		return modelMapper.map(todoUpdated, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todorepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found by id : " + id));

		todorepository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todorepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found by id :" + id));

		todo.setStatus(Boolean.TRUE);

		Todo completeTodo = todorepository.save(todo);

		return modelMapper.map(completeTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo todo = todorepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found by id :" + id));

		todo.setStatus(Boolean.FALSE);

		Todo incompleteTodo = todorepository.save(todo);

		return modelMapper.map(incompleteTodo, TodoDto.class);
	}

}
