package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/author")
    public List<Author> getAuthor() {
        return authorRepository.findAll(PageRequest.of(0, 10)).getContent();
    }
}
