package com.oocl.todolistapi;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TodoIntegrationTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown(){
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todos_when_getAllTodos() throws Exception {
        // given
        Todo todo = new Todo(1,"Test",false);
        todoRepository.save(todo);
        // when and then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].todoItem").value("Test"))
                .andExpect(jsonPath("$[0].done").isBoolean());
    }

    @Test
    void should_return_todo_when_createTodos_given_todo_request() throws Exception {
        String todoRequestAsJson = " {\n" +
                "    \"todoItem\": \"Test\",\n" +
                "    \"done\": false\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoRequestAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.todoItem").value("Test"))
                .andExpect(jsonPath("$.done").isBoolean());
    }

    @Test
    void should_return_update_todo_when_updateTodo_given_todoId_and_todo_request() throws Exception {
        Todo todo = new Todo(1,"Test",false);
        todoRepository.save(todo);

        String todoRequestAsJson = "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"todoItem\": \"Test\",\n" +
                "    \"done\": true\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todos/{todoId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoRequestAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.todoItem").value("Test"))
                .andExpect(jsonPath("$.done").value(true));
    }
}
