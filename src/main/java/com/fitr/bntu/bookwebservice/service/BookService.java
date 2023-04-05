package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.entity.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    BookDTO add(BookDTO bookDTO); // админу может добавлять книги

    BookDTO update(BookDTO bookDTO);

    void deleteById(Integer id); // админу может удалять книги

    BookDTO update(Integer id, String name, AuthorDTO author, BigDecimal cost, GenreDTO genre, String imagePath); // админу может редачить книги

    List<BookDTO> findAll(int pageNumber, int numberOfElementsPerPage); // админу и юзеру чтоб глянуть фул список имеющихся книг

    List<BookDTO> findAllByParameters(String name, Author author, Genre genre, int pageNumber, int numberOfElementsPerPage); // поиск для админа и юзера из фул списка
    List<BookDTO> findAllByParameters2(String name, int pageNumber, int numberOfElementsPerPage);
    BookDTO findById(Integer id); // для страницы книги
}
