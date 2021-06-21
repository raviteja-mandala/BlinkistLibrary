package com.example.zemoso.library;

import com.example.zemoso.library.exception.AuthorAlreadyExistsException;
import com.example.zemoso.library.exception.BookNotFoundException;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("junits")
@AutoConfigureMockMvc
@Sql(scripts = "classpath:data-tests.sql")
public class AuthorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewAuthor() throws Exception {
        String authorJson = "{\"authorId\" : 322,\"name\":\"Surya\",\"bookIds\":[17],\"emailId\":\"surya@xyz.com\",\"phoneNumber\":\"9898980099\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/authors")
                .accept(MediaType.APPLICATION_JSON).content(authorJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andExpect(
                MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo("Surya")));
    }

    @Test
    public void addExistingAuthor() throws Exception {

        String authorJson = "{\"authorId\":29,\"name\":\"Surya\",\"emailId\":\"surya@xyz.com\",\"phoneNumber\":\"9898980099\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/authors")
                .accept(MediaType.APPLICATION_JSON).content(authorJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AuthorAlreadyExistsException));
    }

    @Test
    public void addAuthorWithInvalidBook() throws Exception {

        String bookJson = "{\"name\":\"Dhoni\",\"emailId\":\"dhoni@xyz.com\",\"phoneNumber\":\"9898980099\",\"bookIds\":[87]}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/authors")
                .accept(MediaType.APPLICATION_JSON).content(bookJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookNotFoundException));
    }

    @Test
    public void getAllAuthors() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/blinkist/authors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
}
