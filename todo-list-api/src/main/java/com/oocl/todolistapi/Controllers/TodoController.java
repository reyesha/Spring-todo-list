package com.oocl.todolistapi.Controllers;

import com.oocl.todolistapi.dto.TodoRequest;
import com.oocl.todolistapi.dto.TodoResponse;
import com.oocl.todolistapi.mapper.TodoMapper;
import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")

public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return todos.stream().map(todoMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoRequest todoRequest) {
        Todo saveTodo = todoService.createTodo(todoMapper.toEntity(todoRequest));
        return todoMapper.toResponse(saveTodo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Integer todoId) {
        todoService.deleteTodo (todoId);
    }

    @PutMapping("/{todoId}")
    public TodoResponse updateTodo(@PathVariable(required = true) Integer todoId,
                                   @RequestBody(required = true) TodoRequest todoUpdate) {
        return todoMapper.toResponse(todoService.updateTodo(todoId, todoMapper.toEntity(todoUpdate)));
    }
}
