package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.AuthorDTO;
import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.service.AuthorService;
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
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void addAuthorPositive(){
        AuthorDTO expected = new AuthorDTO(0, "Алексей", "Иванов");
        AuthorDTO actual = authorService.add("Алексей", "Иванов");
        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDataForAddAuthor")
    void addAuthorNegative(String name, String lastName) {
        assertThrows(ServiceException.class, ()-> authorService.add(name, lastName));
    }
    private static Stream<Arguments> provideInvalidDataForAddAuthor() {
        return Stream.of(
                Arguments.of( "автор1", "ласт наме"),
                Arguments.of("автор invalid", "lastname1"),
                Arguments.of("автор!", "password@"),
                Arguments.of("а", "p"),
                Arguments.of("фывпвыапофцдбукецукщопитоыьвдьмиагштьычьSADFGSHGDFGFFSDNGFGDGFHGSFDвыыаигмнтсьывмаивгсыьвфмивтльвфыат", "lastNmae@"),
                Arguments.of("Alex", "asdasdfsdjignksfdnghdnomdsdvufndmMASDBFVNDCSMADGFGFAAGFFGDSSFGFHDDFBFDNSDVGSFDFGEFWGFGFGASFpasswosdsdfdfsfd  ")
        );
    }
    @Test
    void findAllPositive() {
        AuthorDTO author1 = new AuthorDTO(1, "Levon", "Orshan");
        AuthorDTO author2 = new AuthorDTO(2, "Raul", "Ostin");
        List<AuthorDTO> expected = new ArrayList<>();
        expected.add(author1);
        expected.add(author2);
        List<AuthorDTO> actual = authorService.findAll(1, 20);
        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDataForFindAll")
    void findAllNegative(int page, int amount) {
        assertThrows(ServiceException.class, ()-> authorService.findAll(page, amount));
    }

    public static Stream<Arguments> provideInvalidDataForFindAll(){
        return Stream.of(
                Arguments.of( 0, 1),
                Arguments.of( 1, 0)
        );
    }
    @Test
    void findByIdPositive() {
        AuthorDTO expected = new AuthorDTO(1, "Levon", "Orshan");
        AuthorDTO actual = authorService.findById(1);
        assertEquals(expected, actual);
    }
    @Test
    void findByIdNegative() {
        int idNotInDb = 1000;
        assertThrows(ServiceException.class, ()->authorService.findById(idNotInDb));
    }

}