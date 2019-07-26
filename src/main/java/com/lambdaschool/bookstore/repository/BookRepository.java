package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
}
