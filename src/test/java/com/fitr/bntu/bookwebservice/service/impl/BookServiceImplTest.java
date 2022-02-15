package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Genre;
import com.fitr.bntu.bookwebservice.service.BookService;
import com.fitr.bntu.bookwebservice.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("Test")
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    void addPositive() {
        BookDTO expected = new BookDTO(0, "Lord of Ring", "sdfasd.jpg", new BigDecimal("20.20"), new GenreDTO(1,"Фантастика"), new AuthorDTO(1,"Лев", "Толстой"), null);
        BookDTO actual = bookService.add(new BookDTO(0,"Lord of Ring","sdfasd.jpg", new BigDecimal("20.20"), new GenreDTO(1,"Фантастика"),new AuthorDTO( 1,"Лев", "Толстой"),null) );
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDataForAdd")
    void addNegative(String name, String authorName, String authorLastName, BigDecimal cost, GenreDTO genre, String imagePath) {
        assertThrows(ServiceException.class, ()-> bookService.add(new BookDTO( 0,name,imagePath, cost,genre,new AuthorDTO(1,authorName, authorLastName),null)));
    }

    @Test
    void updatePositive() {
        BookDTO expected = new BookDTO(1, "Lord of Ring", "sdfasd.jpg", new BigDecimal("20.20"), new GenreDTO(1,"Фантастика"), new AuthorDTO(1,"Лев", "Толстой"), false);
        BookDTO actual = bookService.update(1,"Lord of Ring",new AuthorDTO(1,"Лев", "Толстой"),  new BigDecimal("20.20"), new GenreDTO(1,"Фантастика"),"sdfasd.jpg");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDataForUpdate")
    void updateNegative(Integer id, String name, String authorName, String authorLastName, BigDecimal cost, GenreDTO genre, String imagePath) {
        assertThrows(ServiceException.class, ()-> bookService.update(id, name,new AuthorDTO(1,authorName, authorLastName), cost, genre, imagePath));
    }

    @Test
    void findAllByParameters(){
        BookDTO book1 = new BookDTO(1, "Skeleton king", "defaultpath.jpg", new BigDecimal("40.50"), new GenreDTO(1, "Fantasy"), new AuthorDTO(1, "Levon", "Orshan"),false);
        List<BookDTO> expected = new ArrayList<>();
        expected.add(book1);
        List<BookDTO> actual = bookService.findAllByParameters("Skeleton king",new Author(1,"Levon", "Orshan"), new Genre(1, "Fantasy"), 1,20);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDataForFindAllByParameters")
    void findAllByParametersNegative(String name, String authorName, String authorLastName, Genre genre, int pageNumber, int numberOfElementsPerPage) {
        assertThrows(ServiceException.class, ()-> bookService.findAllByParameters(name, new Author(1,authorName, authorLastName),  genre, pageNumber, numberOfElementsPerPage));
    }
    public static Stream<Arguments> provideInvalidDataForFindAllByParameters(){
        return Stream.of(
                Arguments.of("21432@", "a", "lsd sd", new Genre(4,"Poesiya"), 0, 1),
                Arguments.of("s/", "sdgsfdgsfdgdsfgdsfgdsfgdsfgtrhwertwertdsgfdgfdgsdfgfdgfdsgfsdgfsdgfsdgdsfgsfdgdsfgfsdgfsdgsfdgdsfgsdgsdgsfdgfdsgsfdgfsdg", "12", new Genre(5,"Psyhology"), 1, 0)
        );
    }

    @Test
    void findAllPositive() {
        BookDTO book1 = new BookDTO(1, "Skeleton king", "defaultpath.jpg", new BigDecimal("40.50"), new GenreDTO(1, "Fantasy"), new AuthorDTO(1, "Levon", "Orshan"),false);
        BookDTO book2 = new BookDTO(2, "Cut eye", "defaultpath.jpg", new BigDecimal("50.60"), new GenreDTO(2, "Horror"), new AuthorDTO(2, "Raul", "Ostin"), false);
        List<BookDTO> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);
        List<BookDTO> actual = bookService.findAll(1, 20);
        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDataForFindAll")
    void findAllNegative(int page, int amount) {
        assertThrows(ServiceException.class, ()-> bookService.findAll(page, amount));
    }

    public static Stream<Arguments> provideInvalidDataForFindAll(){
        return Stream.of(
                Arguments.of( 0, 1),
                Arguments.of( 1, 0)
        );
    }

    @Test
    void findByIdPositive() {
        BookDTO expected = new BookDTO(1, "Skeleton king", "defaultpath.jpg", new BigDecimal("40.50"), new GenreDTO(1, "Fantasy"), new AuthorDTO(1, "Levon", "Orshan"), false);
        BookDTO actual = bookService.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdNegative() {
        int idNotInDb = 1000;
        assertThrows(ServiceException.class, ()->bookService.findById(idNotInDb));
    }

    private static Stream<Arguments> provideInvalidDataForUpdate() {
        return Stream.of(
                Arguments.of( 0, "logom_", "pas23","lafcrafts4","0",new GenreDTO(0,"УЖасы"), ""),
                Arguments.of( 1, "lOunge ", "pas@234","lafcrafts4","-1",new GenreDTO(1,"УЖ  асы"), "asd"),
                Arguments.of( 2, "london%", "pas$","lafcrafts4","-2",new GenreDTO(2,"УЖас@ыq434"), "sadf."),
                Arguments.of( 3, "lorain/3", "p","lafcrafts4","100.100",new GenreDTO(3,"УЖас$ы"), "/dsa"),
                Arguments.of( 4, "12345678910qwertyuiop1234567890qwertyuiopqwertyuiopasdfghjklzx", "pas234asdfasdfasdfsadfwerterwgfsdgdryersrtweredsgererrhytrhfdb","lafcrafts4","10000000",new GenreDTO(4,"УЖас%ы"), "asq12")
        );
    }

    private static Stream<Arguments> provideInvalidDataForAdd() {
        return Stream.of(
                Arguments.of( "logom_", "pas23","lafcrafts4","0",new GenreDTO(0,"УЖасы"), ""),
                Arguments.of( "lOunge ", "pas@234","lafcrafts4","-1",new GenreDTO(1,"УЖ  асы"), "asd"),
                Arguments.of( "london%", "pas$","lafcrafts4","-2",new GenreDTO(2,"УЖас@ыq434"), "sadf."),
                Arguments.of( "lorain/3", "p","lafcrafts4","100.100",new GenreDTO(3,"УЖас$ы"), "/dsa"),
                Arguments.of( "12345678910qwertyuiop1234567890qwertyuiopqwertyuiopasdfghjklzx", "pas234asdfasdfasdfsadfwerterwgfsdgdryersrtweredsgererrhytrhfdb","lafcrafts4","10000000",new GenreDTO(4,"УЖас%ы"), "asq12")
        );
    }
}