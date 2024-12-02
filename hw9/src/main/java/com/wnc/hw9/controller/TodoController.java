package com.wnc.hw9.controller;

import com.wnc.hw9.dto.TodoDto;
import com.wnc.hw9.entity.Todo;
import com.wnc.hw9.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // GET all todos
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos().stream()
                .map(todo -> new Todo(todo.getId(), todo.getTodoItem(), todo.isCompleted()))
                .collect(Collectors.toList());
    }

    // POST new todo
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        Todo savedTodo = todoService.createTodo(todoDto);
        TodoDto savedTodoDto = new TodoDto(savedTodo.getTodoItem(), savedTodo.isCompleted());
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    // PUT update todo
    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable long id, @RequestBody TodoDto todoDto) {
        Todo updatedTodo = todoService.updateTodo(id, todoDto);
        return updatedTodo != null ?
                ResponseEntity.ok(new TodoDto(updatedTodo.getTodoItem(), updatedTodo.isCompleted())) :
                ResponseEntity.notFound().build();
    }

    // DELETE todo by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        return todoService.deleteTodo(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
