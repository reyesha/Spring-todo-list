package com.oocl.todolistapi.model;

import javax.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "todo_seq")
    @SequenceGenerator(name = "todo_seq", allocationSize = 1)
    private Integer id;
    private String todoItem;
    private boolean isDone;

    public Todo() {

    }

    public Todo(Integer id, String todoItem, boolean isDone) {
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
