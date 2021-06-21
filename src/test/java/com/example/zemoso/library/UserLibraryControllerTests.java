package com.example.zemoso.library;

import com.example.zemoso.library.exception.BookAlreadyInUserLibraryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("junits")
@AutoConfigureMockMvc
@Transactional
class UserLibraryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewBookToUser() throws Exception {
        String userBookJson = "{\"userId\":111,\"bookId\":17}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/userLibrary")
                .accept(MediaType.APPLICATION_JSON).content(userBookJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(
                MockMvcResultMatchers.jsonPath("$.userBookId").exists());
    }

    @Test
    void addExistingBookToUser() throws Exception {
        String userBookJson = "{\"userId\":112,\"bookId\":17}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/userLibrary")
                .accept(MediaType.APPLICATION_JSON).content(userBookJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(result -> assertTrue(result.getResolvedException() instanceof BookAlreadyInUserLibraryException));
    }

    @Test
    void deleteBookForUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/blinkist/userLibrary/112/book/17")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult requestResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals("Book successfully deleted for user!",result);
    }
}
