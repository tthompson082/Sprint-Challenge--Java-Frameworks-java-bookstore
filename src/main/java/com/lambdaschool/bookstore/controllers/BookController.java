package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Authors;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.ErrorDetail;
import com.lambdaschool.bookstore.services.AuthorsService;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class BookController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorsService authorsService;

    @ApiOperation(value = "Returns all Books", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Books Found", responseContainer = "List", response = Book.class), @ApiResponse(code = 500, message = "Error retrieving Courses", response = ErrorDetail.class)})
    @ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 5) Pageable pageable)
    {
        ArrayList<Book> myBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all Authors", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Authors Found", responseContainer = "List", response = Authors.class), @ApiResponse(code = 500, message = "Error retrieving Courses", response = ErrorDetail.class)})
    @ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 5) Pageable pageable)
    {
        ArrayList<Authors> myAuthors = authorsService.findAll(pageable);
        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a Book")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Book Updated"), @ApiResponse(code = 500, message = "Error retrieving Courses", response = ErrorDetail.class)})
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable long id)
    {
        bookService.infoUpdate(book, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Post an Author to a Book")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Book Updated"), @ApiResponse(code = 500, message = "Error retrieving Courses", response = ErrorDetail.class)})
    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}", produces = {"application/json"})
    public ResponseEntity<?> updateWrote(@PathVariable long bookid, @PathVariable long authorid)
    {
        Book updatedBook = bookService.update(bookid, authorid);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a Book")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Book Deleted"), @ApiResponse(code = 500, message = "Error retrieving Courses", response = ErrorDetail.class)})
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
