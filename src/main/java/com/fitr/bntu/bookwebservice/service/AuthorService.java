package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.entity.Author;

import java.util.List;

public interface AuthorService {
    Author add(String name, String lastName); //админу при добавлении книги

    Author update(Integer id, String name, String lastName); //при изменении книги админу

    List<Author> findAll(int pageNumber, int numberOfElementsPerPage); // посмотреть всех

    Author findById(Integer id); // мб админу, не знаю для чего

    boolean isExists(Author author); //проверить есть ли такой в списке чтоб при добавлении не содавать такого же , админу
}
