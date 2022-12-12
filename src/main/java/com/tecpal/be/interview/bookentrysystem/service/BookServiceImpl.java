package com.tecpal.be.interview.bookentrysystem.service;

import com.tecpal.be.interview.bookentrysystem.entity.Book;
import com.tecpal.be.interview.bookentrysystem.repository.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book getById(String uuid) {
        return bookMapper.getById(uuid);
    }

    @Override
    public List<Book> getList() {
        return bookMapper.getList();
    }

    @Override
    public void add(Book book) {
        book.setUuid(UUID.randomUUID().toString());
        bookMapper.add(book);
    }

    @Override
    public void delete(String uuid) {
        bookMapper.delete(uuid);
    }
}
