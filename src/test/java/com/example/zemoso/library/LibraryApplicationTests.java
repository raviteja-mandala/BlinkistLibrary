package com.example.zemoso.library;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
