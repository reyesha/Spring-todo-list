package com.oocl.todolistapi.dto;

public class TodoResponse {
    private Integer id;
    private String todoItem;
    private boolean isDone;

    public TodoResponse() {
    }

    public TodoResponse(Integer id, String todoItem, boolean isDone) {
        this.id = id;
        this.todoItem = todoItem;
        this.isDone = isDone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(String todoItem) {
        this.todoItem = todoItem;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
