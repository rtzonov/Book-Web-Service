package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.entity.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    Book add(String name, String authorName, String authorLastName, BigDecimal cost, Genre genre, String imagePath); // админу может добавлять книги

    void deleteById(Integer id); // админу может удалять книги

    Book update(Integer id, String name, String authorName, String authorLastName, BigDecimal cost, Genre genre, String imagePath); // админу может редачить книги

    List<Book> findAll(int pageNumber, int numberOfElementsPerPage); // админу и юзеру чтоб глянуть фул список имеющихся книг

    List<Book> findAllByParameters(String name, String authorName, String authorLastName, Genre genre, int pageNumber, int numberOfElementsPerPage); // поиск для админа и юзера из фул списка

    Book findById(Integer id); // для страницы книги
}
