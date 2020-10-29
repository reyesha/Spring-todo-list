package com.oocl.todolistapi;

import com.oocl.todolistapi.model.Todo;
import com.oocl.todolistapi.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
}
