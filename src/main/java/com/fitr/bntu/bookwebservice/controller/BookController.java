package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import com.fitr.bntu.bookwebservice.data.BookDataForSearch;
import com.fitr.bntu.bookwebservice.repository.AuthorRepository;
import com.fitr.bntu.bookwebservice.repository.BookRepository;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
/*@RequestMapping("/book")*/
@Log4j2
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FileSaver fileSaver;

    @GetMapping("/menu")
    public ModelAndView menu(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "20") Integer amount) {
        log.debug(bookService.findAll(page, amount));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main3");
        mv.addObject("books", bookService.findAll(page, amount));
        mv.addObject("authors", authorService.findAll(page, amount));
        mv.addObject("genres", genreRepository.findAll());
        return mv;
    }
    //это порешать сложно но нужно репозиторий покавырять и тд а в конце концов и сервсиы делать, но я въебу 0
    @PostMapping("/find_book")
    public ModelAndView findBook(BookDataForSearch bookData, HttpSession session,
                                 @RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "13") Integer amount) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main3");
/*        mv.addObject("books", bookService.findAllByParameters(bookData.getName(), bookData.getAuthor(),
                bookData.getGenre(),
                page,
                amount));*/
        mv.addObject("books", bookService.findAllByParameters2(bookData.getName(),
                page,
                amount));
        return mv;
    }

    @PostMapping("/new_book")
    public String createBook(BookData bookData, HttpSession session) {
        log.info("" + bookData.getImageFile());
        try {
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute(SessionAttribute.USER);
                if (user != null && "Admin".equals(user.getRole().getRole())) {
                    MultipartFile image = bookData.getImageFile();
                    String filePath = uploadImage(image);
                    log.debug(bookData);
                    BookDTO book = bookService.add(new BookDTO(

                            0,
                            bookData.getName(),
                            filePath,
                            bookData.getCost(),
                            mapper.map(genreRepository.findById(Integer.parseInt(bookData.getGenre())).get(), GenreDTO.class),
                            authorService.add(new AuthorDTO(0,bookData.getAuthorName(),bookData.getAuthorLastname())),
                            false));
                }
            }
        } catch (SecurityException e) {
            log.error("ошибка случилась");
        }
        /*return "redirect:/book/menu";*/
        return "redirect:/menu";
    }

    //



/*    @GetMapping("/main3")
    public ModelAndView payment2(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "20") Integer amount)
    {
        log.debug(bookService.findAll(page, amount));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main3");
        mv.addObject("books", bookService.findAll(page, amount));
        mv.addObject("authors", authorService.findAll(page, amount));
        mv.addObject("genres", genreRepository.findAll());
        return mv;
    }*/
    //

    @PostMapping("/update_book/{id}")
    public String updateBook(BookData bookData,
                             @PathVariable Integer id,
                             HttpSession session) {
        try {
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute(SessionAttribute.USER);
                if (user != null && "Admin".equals(user.getRole().getRole())) {
                    String imagePath = uploadImage(bookData.getImageFile());
                    log.debug(bookData);
                    BookDTO book = bookService.update(bookData.getId(),
                            bookData.getName(),
                            authorService.findById(Integer.parseInt(bookData.getAuthor())),
                            bookData.getCost(),
                            mapper.map(genreRepository.findById(Integer.parseInt(bookData.getGenre())).get(), GenreDTO.class),
                            // создай ебаный сервис не позорься пожалуйста
                            imagePath);
                }
            }
        } catch (SecurityException e) {
            log.error("ошибка случилась");
        }
        /*return "redirect:/book/menu";*/
        return "redirect:/menu";

    }
//переделать чтоб можно было не зареганым добавлять в корзину
    @GetMapping("/add_to_cart/{id}")
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        log.debug(id);
        List<BookDTO> result = ((List<BookDTO>)Optional.ofNullable(session.getAttribute(SessionAttribute.CART)).orElse(new ArrayList<BookDTO>()));
                    result.add(bookService.findById(id));
        session.setAttribute(SessionAttribute.CART, result);
        /*return "redirect:/book/menu";*/
        return "redirect:/menu";
    }

    @GetMapping("/remove_from_cart/{id}")
    public String removeFromCart(@PathVariable Integer id, HttpSession session) {
        //if (session.getAttribute(SessionAttribute.USER) != null) {
            ((List<BookDTO>) session.getAttribute(SessionAttribute.CART))
                    .removeIf(e -> e.getId() == id);
        //}
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        BookDTO bookToDelete = bookService.findById(id);
        bookToDelete.setIsDeleted(true);
        bookService.update(bookToDelete);
        /*return "redirect:/book/menu";*/
        return "redirect:/menu";
    }

    private String uploadImage(MultipartFile file) {
        if (!StringUtils.isEmpty(file) && !file.isEmpty()) {
            try {
                return fileSaver.saveFile(file);
            } catch (IOException e) {
                log.error("ошибка произошла");
            }
        }
        return null;
    }



}
