package com.tecpal.be.interview.bookentrysystem.repository;

import com.tecpal.be.interview.bookentrysystem.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> getList();
    Book getById(@Param("UUID") String uuid);
    void add(Book book);
    void delete(@Param("UUID") String uuid);
}
