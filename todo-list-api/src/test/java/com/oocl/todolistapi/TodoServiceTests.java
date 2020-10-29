package com.oocl.todolistapi;


import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import com.oocl.todolistapi.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TodoServiceTests {
    TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    List<Todo> expectedTodo = Arrays.asList(new Todo(), new Todo());

    @Test
    void should_return_todos_when_getAllTodos() {
        // given
        when(todoRepository.findAll()).thenReturn(expectedTodo);
        TodoService todoService = new TodoService(todoRepository);
        // when
        List<Todo> actualTodos = todoService.getAllTodos();

        // then
        assertEquals(expectedTodo.size(),actualTodos.size());
    }
}
