package com.roopy.service.impl;

import com.roopy.entity.Todo;
import com.roopy.repository.TodoRepository;
import com.roopy.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Flux<Todo> findTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Mono<Todo> findTodoById(int id) {
        return todoRepository.findById(id);
    }

    @Override
    public Mono<Todo> createTodo(final Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Mono<Todo> updateTodoById(int id, final Todo newTodo) {
        return todoRepository.findById(id)
                .flatMap(orgTodo -> {
                    orgTodo.setCompleted(newTodo.isCompleted());
                    return todoRepository.save(orgTodo);
                });
    }

    @Override
    public Mono<Void> deleteTodoById(int id) {
        return todoRepository.deleteById(id);
    }
}
