package com.roopy.controller;

import com.roopy.entity.Todo;
import com.roopy.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public Flux<Todo> findAllTodos() {
        return todoService.findTodos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Todo>> findTodoById(@PathVariable int id) {
        return todoService.findTodoById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Todo> createTodo(@RequestBody Mono<Todo> todo) {
        return todo.flatMap(todoService::createTodo);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Todo>> updateTodoById(@PathVariable Integer id, @RequestBody Todo todo) {
        return todoService.updateTodoById(id,todo)
                .map(updateTodo -> ResponseEntity.ok(updateTodo))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTodoById(@PathVariable Integer id) {
        return todoService.deleteTodoById(id);
    }
}
