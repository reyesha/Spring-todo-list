package com.oocl.todolistapi.service;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todoRequest) {
        return todoRepository.save(todoRequest);
    }

    public void deleteTodo(Integer todoId) {
        todoRepository.deleteById(todoId);
    }

    public Todo updateTodo(Integer todoId, Todo todoUpdate) {
        return todoRepository.save(todoUpdate);
    }
}
