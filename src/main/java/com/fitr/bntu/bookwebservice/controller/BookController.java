package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Book;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import com.fitr.bntu.bookwebservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getBook() {
        return bookRepository.findAll(PageRequest.of(0, 10)).getContent();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable("id") int id) {
        return bookRepository.findById(id).get();
    }

    @GetMapping("/Author")
    public List<Book> findByAuthor(@RequestParam(name = "Author") Author author) {
        return bookRepository.findAllByAuthor(author);
    }

}
