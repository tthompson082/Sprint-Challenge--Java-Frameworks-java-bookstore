package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Authors;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.AuthorsRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public ArrayList<Book> findAll(Pageable pageable)
    {
        ArrayList<Book> list = new ArrayList<>();
        bookRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Book update(long bookId, long authorId)
    {
        Book currentBook = bookRepository.findByBookid(bookId);
        Authors currentAuthor = authorsRepository.findByAuthorid(authorId);

        currentBook.getAuthors().add(currentAuthor);

        return currentBook;
    }

    @Transactional
    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();
        newBook.setBooktitle(book.getBooktitle());
        newBook.setCopy(book.getCopy());
        newBook.setISBN(book.getISBN());

        ArrayList<Authors> newAuthors = new ArrayList<>();
        for (Authors a : book.getAuthors())
        {
            newAuthors.add(new Authors(a.getLastname(), a.getFirstname()));
        }
        newBook.setAuthors(newAuthors);

        return bookRepository.save(newBook);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Book infoUpdate(Book book, long id)
    {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (currentBook != null)
        {
            if (id == currentBook.getBookid())
            {
                if (book.getBooktitle() != null)
                {
                    currentBook.setBooktitle(book.getBooktitle());
                }

                if (book.getISBN() != null)
                {
                    currentBook.setISBN(book.getISBN());
                }

                if (book.getCopy() != 0)
                {
                    currentBook.setCopy(book.getCopy());
                }
            }
        }

        return bookRepository.save(currentBook);
    }
}
