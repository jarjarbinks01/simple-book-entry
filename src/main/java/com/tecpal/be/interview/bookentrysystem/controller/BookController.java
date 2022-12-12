package com.tecpal.be.interview.bookentrysystem.controller;

import com.tecpal.be.interview.bookentrysystem.entity.Book;
import com.tecpal.be.interview.bookentrysystem.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/")
    public String getHomepage(){
        return "Hi There!"+"<br>"+
                "Get book List - GET: /v1/book"+"<br>"+
                "Get a specific book by UUID - GET: /v1/book/{uuid}"+"<br>"+
                "Add a new book - POST: /v1/book"+"<br>"+
                "Delete a book by UUID - DELETE: /v1/book?uuid={uuid}";
    }

    @GetMapping("/v1/book")
    public List<Book> getBookList(){
        return bookService.getList();
    }

    @GetMapping("/v1/book/{uuid}")
    public Book getById(@PathVariable String uuid){
        Book book = bookService.getById(uuid);
        if(book == null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return book;
    }

    @PostMapping("/v1/book")
    public void addNewBook(@RequestBody @Valid Book book){
        bookService.add(book);
    }
    @DeleteMapping("/v1/book")
    public void deleteBook(@RequestParam String uuid){
        Book book = bookService.getById(uuid);
        if(book == null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        bookService.delete(uuid);
    }
}
