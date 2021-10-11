package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.entity.Genre;

import java.util.List;

@Deprecated
public interface GenreService {
    Genre add(String type); //думал сделать как енам , пока не знаю

    Genre edit(Integer id, String type);

    List<Genre> findAll(int pageNumber, int numberOfElementsPerPage);

    void deleteById(Integer id);

    Genre findById(Integer id);
}
