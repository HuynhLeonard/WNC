package com.wnc.hw9.service;

import com.wnc.hw9.dto.TodoDto;
import com.wnc.hw9.entity.Todo;
import com.wnc.hw9.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Get todo by id
    public Optional<Todo> getTodoById(long id) {
        return todoRepository.findById(id);
    }

    // Create new todo
    public Todo createTodo(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setTodoItem(todoDto.getTodoItem());
        todo.setCompleted(todoDto.isCompleted());
        return todoRepository.save(todo);
    }

    // Update todo
    public Todo updateTodo(long id, TodoDto todoDto) {
        Optional<Todo> existingTodoOptional = todoRepository.findById(id);
        if (!existingTodoOptional.isPresent()) {
            return null;
        }
        Todo existingTodo = existingTodoOptional.get();
        if (todoDto.getTodoItem() != null) {
            existingTodo.setTodoItem(todoDto.getTodoItem());
        }
        if (todoDto.isCompleted() != existingTodo.isCompleted()) {
            existingTodo.setCompleted(todoDto.isCompleted());
        }
        return todoRepository.save(existingTodo);
    }

    // Delete todo
    public boolean deleteTodo(long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()) {
            todoRepository.delete(todo.get());
            return true;
        }
        return false;
    }
}
