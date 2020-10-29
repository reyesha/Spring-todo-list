package com.oocl.todolistapi.Controllers;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")

public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody Todo todoRequest) {
        return todoService.createTodo (todoRequest);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Integer todoId) {
        todoService.deleteTodo (todoId);
    }

    @PutMapping("/{todoId}")
    public Todo updateTodo(@PathVariable(required = true) Integer todoId,
                                   @RequestBody(required = true) Todo todoUpdate) {
        return todoService.updateTodo(todoId, todoUpdate);
    }

}
