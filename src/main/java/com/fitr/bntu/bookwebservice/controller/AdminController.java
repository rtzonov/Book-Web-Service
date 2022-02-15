package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.service.BookService;
import com.fitr.bntu.bookwebservice.util.SessionAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/Admin")
@Controller
public class AdminController {
    @Autowired
    private BookService bookService;


}
