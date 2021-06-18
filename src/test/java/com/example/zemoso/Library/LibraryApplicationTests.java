package com.example.zemoso.Library;

import com.example.zemoso.Library.entity.Book;
import com.example.zemoso.Library.repository.BookRepository;
import com.example.zemoso.Library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({BookControllerTests.class,UserLibraryControllerTests.class})
class LibraryApplicationTests {

	/*@Test
	void contextLoads() {
		assertEquals("abc","abc");
	}*/

	/*@Mock
	BookRepository bookRepository;

	@InjectMocks
	BookService bookService;

	@Test
	public void testBookService(){
		Book book =new Book();
		book.setAuthorId(2);
		book.setBookName("Chemistry");
		book.setBookId(21);
		when(bookRepository.findByBookNameAndAuthorId("Chemistry",2)).thenReturn(Arrays.asList(book));
		List<Book> books=bookService.getBookById("Chemistry", 2);
		assertEquals(books.size(),1);

	}*/


}
