package com.roopy.repository;

import com.roopy.entity.Todo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TodoRepository extends ReactiveCrudRepository<Todo,Integer> {
}
