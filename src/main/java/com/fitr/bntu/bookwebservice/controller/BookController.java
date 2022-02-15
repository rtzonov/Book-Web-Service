package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import com.fitr.bntu.bookwebservice.data.BookDataForSearch;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.data.BookData;
import com.fitr.bntu.bookwebservice.repository.GenreRepository;
import com.fitr.bntu.bookwebservice.service.AuthorService;
import com.fitr.bntu.bookwebservice.service.BookService;
import com.fitr.bntu.bookwebservice.util.FileSaver;
import com.fitr.bntu.bookwebservice.util.SessionAttribute;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;


}
