package com.tecpal.be.interview.bookentrysystem.service;

import com.tecpal.be.interview.bookentrysystem.entity.Book;
import com.tecpal.be.interview.bookentrysystem.repository.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceImplTest {


    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookMapper bookMapper;

    @BeforeEach
    public void setUp() {
        Book atomicHabit = new Book(UUID.randomUUID().toString(),
                "Atomic Habit",
                "atomicHabit_description",
                "James Clear",
                "Some Publishers",
                new Date());

        when(bookMapper.getList()).thenReturn(List.of(atomicHabit));
        when(bookMapper.getById(anyString())).thenReturn(atomicHabit);
    }

    @Test
    void get() {
        Book result = bookService.getById("1");
        assertEquals("Atomic Habit", result.getTitle());

    }

    @Test
    void getList() {
        List<Book> result = bookService.getList();
        assertEquals("Atomic Habit", result.get(0).getTitle());
    }

    @Test
    void add() {
        Book newBook = new Book();
        newBook.setTitle("Fairy Tale");
        newBook.setAuthor("Stephen King");
        newBook.setDescription("Description");
        newBook.setPublisher("Scribner");
        newBook.setPublish_date(new Date(System.currentTimeMillis()));
        bookService.add(newBook);
    }

    @Test
    void delete() {
        doNothing().when(bookMapper).delete(isA(String.class));
        bookService.delete("1");
        verify(bookMapper, times(1)).delete(isA(String.class));
    }
}