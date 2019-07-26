package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface BookService
{
    ArrayList<Book> findAll(Pageable pageable);

    Book update(long bookId, long authorId);

    Book save(Book book);

    void delete(long id);
}
