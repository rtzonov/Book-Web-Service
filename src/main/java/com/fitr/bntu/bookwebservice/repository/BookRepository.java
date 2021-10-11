package com.fitr.bntu.bookwebservice.repository;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    @Query (value = "SELECT * from Book b JOIN author a on b.author_id=a.id" +
            " JOIN genre g on b.genre_id = g.id " +
            "WHERE b.name = :name and a.name LIKE :authorName  " +
            "and a.last_name LIKE :authorLastName and g.type = :genre", nativeQuery = true)
    List<Book> findAllByParameters(String name, String authorName, String authorLastName, String genre, int pageNumber, int numberOfElementsPerPage);
}

