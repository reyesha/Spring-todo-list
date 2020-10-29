package com.oocl.todolistapi;


import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import com.oocl.todolistapi.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceTests {
    TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    List<Todo> expectedTodo = Arrays.asList(new Todo(), new Todo());
    Todo todoRequest = new Todo(1,"test",false);
    TodoService todoService = new TodoService(todoRepository);
    @Test
    void should_return_todos_when_getAllTodos() {
        // given
        when(todoRepository.findAll()).thenReturn(expectedTodo);
        // when
        List<Todo> actualTodos = todoService.getAllTodos();

        // then
        assertEquals(expectedTodo.size(),actualTodos.size());
    }

    @Test
    void should_create_todo_when_createTodo_given_todo_request() {
        // given
        when(todoRepository.save(todoRequest)).thenReturn(todoRequest);

        // when
        Todo todoActual = todoService.createTodo(todoRequest);

        // then
        assertEquals(todoRequest.getId(),todoActual.getId());
    }

    @Test
    void should_delete_todo_when_deleteTodo_given_todoId() {
        // given
        when(todoRepository.findById(1)).thenReturn(Optional.of(todoRequest));

        // when
        todoService.deleteTodo(1);

        // then
        verify(todoRepository).deleteById(1);
    }
}
