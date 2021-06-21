package com.example.zemoso.library;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ABCTests {

    @Test
    public void abc1(){
        assertEquals(1,1);
    }

    @Test
    public void abc2(){
        assertEquals(1,1);
    }
}
