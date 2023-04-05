package com.fitr.bntu.bookwebservice.repository;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    @Query (value = "SELECT * FROM book b WHERE author_id IN (SELECT author.id FROM author WHERE author.name=:authorName AND author.last_name=:authorLastName) AND b.name = :name AND b.genre_id IN (SELECT id FROM genre WHERE type =:genre) limit :pageNumber, :numberOfElementsPerPage", nativeQuery = true)
    List<Book> findAllByParameters(String name, String authorName, String authorLastName, String genre, int pageNumber, int numberOfElementsPerPage);
    @Query (value = "SELECT * FROM book b WHERE (author_id IN (SELECT author.id FROM author WHERE author.name like  CONCAT('%',:name,'%') OR author.last_name like CONCAT('%',:name,'%'))) OR b.name like  CONCAT('%',:name,'%') limit :pageNumber, :numberOfElementsPerPage", nativeQuery = true)
    List<Book> findAllByParameters2(String name, int pageNumber, int numberOfElementsPerPage);
    List<Book> findAllByNameContainingAndGenre(String name, Genre genre);
    List<Book> findAllByNameContainingAndAuthor(String name, Author author);
    //List<Book> findAllByAuthor(Author author);
    List<Book> findAllByNameContainingAndAuthorAndGenre(String name, Author author, Genre genre);

    List<Book> findAllByNameContaining(String name);

    List<Book> findAllByIsDeleted(Pageable page, boolean isDeleted);

    List<Book> findAllByAuthor(Author author);

    boolean existsByNameAndAuthor(String name, Author author);
    
}

