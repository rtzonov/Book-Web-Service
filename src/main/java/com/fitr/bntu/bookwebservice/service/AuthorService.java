package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.entity.Author;

import java.util.List;

public interface AuthorService {

    AuthorDTO add(AuthorDTO authorDTO); //админу при добавлении книги

    AuthorDTO update(Integer id, String name, String lastName); //при изменении книги админу

    List<AuthorDTO> findAll(int pageNumber, int numberOfElementsPerPage); // посмотреть всех

    AuthorDTO findById(Integer id); // мб админу, не знаю для чего



    boolean isExists(AuthorDTO author); //проверить есть ли такой в списке чтоб при добавлении не содавать такого же , админу
}
