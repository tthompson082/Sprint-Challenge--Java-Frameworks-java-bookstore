package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Authors;
import com.lambdaschool.bookstore.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "authorsService")
public class AuthorsServiceImpl implements AuthorsService
{
    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public ArrayList<Authors> findAll(Pageable pageable)
    {
        ArrayList<Authors> list = new ArrayList<>();
        authorsRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }
}
