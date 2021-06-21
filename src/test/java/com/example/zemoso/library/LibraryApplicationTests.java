package com.example.zemoso.library;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookControllerTests.class, AuthorControllerTests.class, BookAuthorControllerTests.class, UserLibraryControllerTests.class})
public class LibraryApplicationTests {

}
