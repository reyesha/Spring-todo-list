package com.oocl.todolistapi.dto;

public class TodoRequest {
    private String todoItem;
    private boolean isDone;

    public TodoRequest() {
    }

    public TodoRequest(String todoItem, boolean isDone) {
        this.todoItem = todoItem;
        this.isDone = isDone;
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
