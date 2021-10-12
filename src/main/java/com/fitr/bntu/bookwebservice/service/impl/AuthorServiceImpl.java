package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import com.fitr.bntu.bookwebservice.service.AuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LogManager.getLogger();

    private final AuthorRepository repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author add(String name, String lastName) {
        Author author = new Author(0, name, lastName);
        return repository.save(author);
    }

    @Override
    public Author update(Integer id, String name, String lastName) {
        Author author = new Author(id, name, lastName);
        return repository.save(author);
    }

    @Override
    public List<Author> findAll(int pageNumber, int numberOfElementsPerPage) {
        return (List<Author>) repository.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage));
    }

    @Override
    public Author findById(Integer id) {
        Author author = repository.findById(id).get();
        return author;
    }

    @Override
    // make this method and think about logic
    public boolean isExists(Author author) {

        return true;
    }

}
