package com.example.zemoso.library;

import com.example.zemoso.library.exception.AuthorNotFoundException;
import com.example.zemoso.library.exception.BookAlreadyPresentException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("junits")
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = "classpath:data-tests.sql")
class BookControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBooksByTitleTest() throws Exception {
        //Mockito.when(bookRepository.findByTitleorAuthor("zoology","zoology")).thenReturn(books);
        RequestBuilder requestBuilder = get(
                "/blinkist/books/title/Physics").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].bookId", Matchers.equalTo(17)));
    }

    @Test
    void getBooksByCategory() throws Exception {
        //Mockito.when(bookRepository.findByCategoryName("Education")).thenReturn(books);
        RequestBuilder requestBuilder = get(
                "/blinkist/books/category/Science").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].bookName", Matchers.equalTo("Physics")));
    }

    @Test
    void addNewBook() throws Exception {
        String bookJson = "{\"bookTitle\":\"Biology\",\"authorIds\":[29],\"categoryId\":2,\"categoryName\":\"Medicine\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/books")
                .accept(MediaType.APPLICATION_JSON).content(bookJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andExpect(
                MockMvcResultMatchers.jsonPath("$.bookName", Matchers.equalTo("Biology")));

    }

    @Test
    void addExistingBook() throws Exception {
        String bookJson = "{\"bookId\":17,\"bookTitle\":\"Physics\",\"authorIds\":[201],\"categoryId\":2,\"categoryName\":\"Medicine\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/books")
                .accept(MediaType.APPLICATION_JSON).content(bookJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookAlreadyPresentException));

    }

    @Test
    void addBookWithInvalidAuthor() throws Exception {
        String bookJson = "{\"bookTitle\":\"Statistics\",\"authorIds\":[345],\"categoryId\":2,\"categoryName\":\"Maths\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/blinkist/books")
                .accept(MediaType.APPLICATION_JSON).content(bookJson)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AuthorNotFoundException));

    }


}
