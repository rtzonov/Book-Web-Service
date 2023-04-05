package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.data.AuthorData;
import com.fitr.bntu.bookwebservice.data.BookData;
import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import com.fitr.bntu.bookwebservice.service.AuthorService;
import com.fitr.bntu.bookwebservice.util.SessionAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/author")
    public List<Author> getAuthor() {
        return authorRepository.findAll(PageRequest.of(0, 10)).getContent();
    }
    @PostMapping("/new_Author")
    public String createAuthor(AuthorData authorData, HttpSession session) {
        /*log.info("" + bookData.getImageFile());*/
        try {
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute(SessionAttribute.USER);
                if (user != null && "Admin".equals(user.getRole().getRole())) {
/*                    MultipartFile image = bookData.getImageFile();
                    String filePath = uploadImage(image);
                    log.debug(bookData);*/
                    AuthorDTO author = authorService.add(new AuthorDTO(0,authorData.getName(),authorData.getLastname()));
/*                    BookDTO book = bookService.add(new BookDTO(
                            0,
                            bookData.getName(),
                            filePath,
                            bookData.getCost(),
                            mapper.map(genreRepository.findById(Integer.parseInt(bookData.getGenre())).get(), GenreDTO.class),
                            authorService.findById(Integer.parseInt(bookData.getAuthor())),
                            false));*/
                }
            }
        } catch (SecurityException e) {
            /*log.error("ошибка случилась");*/
        }
        return "redirect:/book/menu";
    }
}
