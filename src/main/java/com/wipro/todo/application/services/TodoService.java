package com.wipro.todo.application.services;

import com.wipro.todo.application.model.Todo;
import com.wipro.todo.application.repositories.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public Optional<Todo> getById(Integer id) {
        return repository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    public Todo updateTodo(Integer id, Todo todo) {
        Optional<Todo> savedTodo = getById(id);
        if (savedTodo.isPresent()) {
            Todo t = savedTodo.get();
            if (todo.getDescription()!= null)
                t.setDescription(todo.getDescription());

            if (todo.getTargetDate() != null)
                t.setTargetDate(todo.getTargetDate());

            return repository.save(t);
        }
        return null;
    }

    public Todo deleteTodo(Integer id) {
        Optional<Todo> todo = getById(id);

        todo.ifPresent(repository::delete);

        return todo.get();
    }
}
