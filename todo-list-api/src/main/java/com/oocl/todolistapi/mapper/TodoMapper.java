package com.oocl.todolistapi.mapper;

import com.oocl.todolistapi.dto.TodoRequest;
import com.oocl.todolistapi.dto.TodoResponse;
import com.oocl.todolistapi.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoResponse toResponse (Todo todo) {
        TodoResponse todoResponse = new TodoResponse();

        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

    public Todo toEntity (TodoRequest todoRequest) {
        Todo todo = new Todo();

        BeanUtils.copyProperties(todoRequest, todo);

        return todo;
    }
}
