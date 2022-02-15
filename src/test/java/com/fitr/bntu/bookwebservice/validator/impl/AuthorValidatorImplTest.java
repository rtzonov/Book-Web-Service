package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.validator.AuthorValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("Test")
class AuthorValidatorImplTest {

    @Autowired
    private AuthorValidator authorValidator;

    @ParameterizedTest
    @ValueSource(strings = {"Artem",
            "Alexander",
            "Оа",
            "йцукенгшщзйцукенгшщзйцукенгшщзйцукенгшщзйцукенгшщзйцукенгшщз",
            "Павел"})
    void isNameValidShouldReturnTrue(String name){
        assertTrue(authorValidator.isNameValid(name));
    }
    @ParameterizedTest
    @ValueSource(strings = {"2345",
            "O 0",
            "r",
            "йцукенгшщзфывапролдячсмитьйцукенгшщзфывапролджячсмитьйцуенгшщзывапролдясмитьйцукенгш",
            "Kao3"})
    void isNameInvalidShouldReturnFalse(String name){
        assertFalse(authorValidator.isNameValid(name));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Petrov",
            "Казлов",
            "Цы",
            "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop",
            "Topolinov"})
    void isLastNameValidShouldReturnTrue(String lastName){
        assertTrue(authorValidator.isLastNameValid(lastName));
    }
    @ParameterizedTest
    @ValueSource(strings = {"346",
            "К",
            "Ц p",
            "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqw",
            "Topolinov234"})
    void isLastNameInvalidShouldReturnFalse(String lastName){
        assertFalse(authorValidator.isLastNameValid(lastName));
    }

    @ParameterizedTest
    @MethodSource("provideValidAuthor")
    void isAuthorValidShouldReturnTrue(String name, String lastName) {
        Author author = new Author(1, name, lastName);
        assertTrue(authorValidator.isAuthorValid(author));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAuthor")
    void isAuthorInvalidShouldReturnFalse(String name, String lastName) {
        Author author = new Author(1, name, lastName);
        assertFalse(authorValidator.isAuthorValid(author));
    }
    private static Stream<Arguments> provideValidAuthor(){
        return Stream.of(
                Arguments.of("Джек","Воробей"),
                Arguments.of("Lord","Ring"),
                Arguments.of("sdgfsdf","xcvbd"),
                Arguments.of("Дж","qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop"),
                Arguments.of("Джеasdк","Воробеasdfй")
        );
    }
    private static Stream<Arguments> provideInvalidAuthor(){
        return Stream.of(
                Arguments.of("Д","Воробей"),
                Arguments.of("Lord","g"),
                Arguments.of("sdgfs2345df","xcv2345bd"),
                Arguments.of("Дж","qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopsdgd"),
                Arguments.of("Джеasdк j","Воробеasdfй")
        );
    }

}