package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Authors;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface AuthorsService
{
    ArrayList<Authors> findAll(Pageable pageable);
}
