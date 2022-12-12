package com.tecpal.be.interview.bookentrysystem.service;

import com.tecpal.be.interview.bookentrysystem.entity.Book;

import java.util.List;

public interface BookService {
    Book getById(String uuid);
    List<Book> getList();
    void add(Book book);
    void delete(String uuid);
}
