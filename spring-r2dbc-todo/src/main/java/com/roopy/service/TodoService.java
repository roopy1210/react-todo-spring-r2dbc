package com.roopy.service;

import com.roopy.entity.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {

    /**
     * Todo 목록 조회
     * 
     * @return Flux<Todo>
     */
    Flux<Todo> findTodos();

    /**
     * Todo 조회
     *
     * @param id - Todo ID
     * @return Mono<Todo>
     */
    Mono<Todo> findTodoById(int id);

    /**
     * Todo 등록
     *
     * @param todo - Mono<Todo>
     * @return Mono<Todo>
     */
    Mono<Todo> createTodo(final Todo todo);

    /**
     * Todo 수정
     *
     * @param id - 업데이트 Todo ID
     * @param todo - Mono<Todo>
     * @return Mono<Todo>
     */
    Mono<Todo> updateTodoById(int id, Todo todo);

    /**
     *
     *
     * @param id - 삭제 Todo ID
     * @return Mono<Void>
     */
    Mono<Void> deleteTodoById(int id);

}
