package com.example.zemoso.library;

import com.example.zemoso.library.exception.AuthorAlreadyAddedToBookException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("junits")
@AutoConfigureMockMvc
@Sql(scripts = "classpath:data-tests.sql")
public class BookAuthorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewAuthorToBook() throws Exception {
        String bookAuthorJson = "{\"authorId\":\"33\",\"bookId\":17}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/book/author")
                .accept(MediaType.APPLICATION_JSON).content(bookAuthorJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void addDuplicateAuthorToBook() throws Exception {
        String bookAuthorJson = "{\"authorId\":\"29\",\"bookId\":17}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/book/author")
                .accept(MediaType.APPLICATION_JSON).content(bookAuthorJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AuthorAlreadyAddedToBookException));
    }

    @Test
    public void deleteAuthorToBook() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/blinkist/book/17/author/29")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalTo("Author 29 is removed from list of authors for book with id 17")));
    }
}
