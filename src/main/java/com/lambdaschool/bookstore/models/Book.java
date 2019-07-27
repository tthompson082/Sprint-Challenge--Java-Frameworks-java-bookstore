package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @Column(name = "title")
    private String booktitle;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "Copy")
    private int copy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "WROTE",
            joinColumns = {@JoinColumn(name = "bookid")},
            inverseJoinColumns = {@JoinColumn(name = "authorid")}
    )
    @JsonIgnoreProperties("books")
    private List<Authors> authors = new ArrayList<>();


    public Book()
    {
    }

    public Book(String booktitle, String ISBN, int copy)
    {
        this.booktitle = booktitle;
        this.ISBN = ISBN;
        this.copy = copy;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<Authors> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Authors> authors)
    {
        this.authors = authors;
    }
}
