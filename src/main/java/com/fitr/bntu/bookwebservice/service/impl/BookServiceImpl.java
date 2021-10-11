package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.entity.Genre;
import com.fitr.bntu.bookwebservice.repository.BookRepository;
import com.fitr.bntu.bookwebservice.service.BookService;
import javafx.scene.control.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private static final Logger logger = LogManager.getLogger();

    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }
    @Override
    public Book add(String name, String authorName, String authorLastName, BigDecimal cost, Genre genre, String imagePath){
        Book book = new Book(0, name, imagePath, cost, genre, authorName, authorLastName );
        return repository.save(book);
    }
    @Override
    public Book update(Integer id, String name, String authorName, String authorLastName, BigDecimal cost, Genre genre, String imagePath){
        Book book = new Book(id, name, imagePath, cost, genre, authorName, authorLastName);
        return repository.save(book);
    }
    @Override
    public List<Book> findAllByParameters(String name, String authorName, String authorLastName, Genre genre, int pageNumber, int numberOfElementsPerPage ) {
        return repository.findAllByParameters(name, authorName, authorLastName, genre.getType(), pageNumber, numberOfElementsPerPage);
    }
    @Override
    public List<Book> findAll(int pageNumber, int numberOfElementsPerPage){
        return (List<Book>) repository.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage));
    }

    @Override
    public Book findById(Integer id) {
        Book book = repository.findById(id).get();
        return book;
    }

    @Override
    public void deleteById(Integer id){
        logger.warn("Deleting certificate with id={}", id);
        repository.deleteById(id);
        logger.warn("Certificate deleted");
    }
}
