package com.tecpal.be.interview.bookentrysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecpal.be.interview.bookentrysystem.entity.Book;
import com.tecpal.be.interview.bookentrysystem.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @MockBean
    private BookServiceImpl bookService;



    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        Book atomicHabit = new Book(UUID.randomUUID().toString(),
                "Atomic Habit",
                "atomicHabit_description",
                "James Clear",
                "Some Publishers",
                new Date());
        when(bookService.getList()).thenReturn(List.of(atomicHabit));
    }

    @Test
    void getHomepage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    void getBookList() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/book")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void getById_found() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Book atomicHabit = new Book(uuid,
                "Atomic Habit",
                "atomicHabit_description",
                "James Clear",
                "Some Publishers",
                new Date());
        when(bookService.getById(uuid)).thenReturn(atomicHabit);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/book/"+uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.uuid", is(uuid)))
                        .andExpect(jsonPath("$.title", is("Atomic Habit")))
                        .andReturn();
    }

    @Test
    void getById_notFound() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Book atomicHabit = new Book(uuid,
                "Atomic Habit",
                "atomicHabit_description",
                "James Clear",
                "Some Publishers",
                new Date());
        when(bookService.getById(uuid)).thenReturn(atomicHabit);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/book/"+ UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();
    }

    @Test
    void addNewBook() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Book atomicHabit = new Book(uuid,
                "Atomic Habit",
                "atomicHabit_description",
                "James Clear",
                "Some Publishers",
                new Date());
        doNothing().when(bookService).add(atomicHabit);
        String requestJson = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(atomicHabit);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    void deleteBook_success() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Book atomicHabit = new Book(uuid,
                "Atomic Habit",
                "atomicHabit_description",
                "James Clear",
                "Some Publishers",
                new Date());
        when(bookService.getById(uuid)).thenReturn(atomicHabit);
        doNothing().when(bookService).delete(uuid);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/book/")
                        .param("uuid", uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    void deleteBook_notFound() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        Book atomicHabit = new Book(uuid,
                "Atomic Habit",
                "atomicHabit_description",
                "James Clear",
                "Some Publishers",
                new Date());
        when(bookService.getById(uuid)).thenReturn(atomicHabit);
        doNothing().when(bookService).delete(uuid2);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/book/")
                        .param("uuid", uuid2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}