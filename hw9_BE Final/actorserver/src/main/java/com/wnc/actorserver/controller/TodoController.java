package com.wnc.actorserver.controller;

import com.wnc.actorserver.dto.TodoDTO;
import com.wnc.actorserver.model.Todo;
import com.wnc.actorserver.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDto) {
        Todo savedTodo = todoService.createTodo(todoDto);
        TodoDTO savedTodoDto = new TodoDTO(savedTodo.getTodoItem(), savedTodo.isCompleted());
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    // PUT update todo
    @PatchMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable long id, @RequestBody TodoDTO todoDto) {
        Todo updatedTodo = todoService.updateTodo(id, todoDto);
        return updatedTodo != null ?
                ResponseEntity.ok(new TodoDTO(updatedTodo.getTodoItem(), updatedTodo.isCompleted())) :
                ResponseEntity.notFound().build();
    }

    // DELETE todo by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        return todoService.deleteTodo(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
