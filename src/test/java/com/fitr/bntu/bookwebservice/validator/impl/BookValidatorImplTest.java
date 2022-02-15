package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Genre;
import com.fitr.bntu.bookwebservice.validator.BookValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("Test")
class BookValidatorImplTest {

    @Autowired
    private BookValidator bookValidator;

    @ParameterizedTest
    @ValueSource(strings = {"Artem",
            "Alexander",
            "Оа",
            "йцукенгшщзйцукенгшщзйцукенгшщзйцукенгшщзйцукенгшщзйцукенгшщз",
            "Павел"})
    void isNameValidShouldReturnTrue(String name) {
        assertTrue(bookValidator.isNameValid(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"234@5",
            "O #0",
            "r",
            "йцукенгшщзфывапролдячсмитьйцукенгшщзфывапролджячсмитьйцуенгшщзывапролдясмитьйцукенгш",
            "Kao3!"})
    void isNameInvalidShouldReturnFalse(String name) {
        assertFalse(bookValidator.isNameValid(name));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Фантастика",
            "ужасы",
            "horror",
            "Fantasy"
    })
    void isGenreValidShouldReturnTrue(String genre){
        assertTrue(bookValidator.isGenreValid(genre));
    }
    @Test
    void isGenreInvalidShouldReturnFalse(){
        assertFalse(bookValidator.isGenreValid(null));
    }
    @ParameterizedTest
    @MethodSource("provideValidDataForCost")
    void isCostValidShouldReturnTrue(BigDecimal cost){
        assertTrue(bookValidator.isCostValid(cost.toString()));
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDataForCost")
    void isCostValidShouldReturnFalse(BigDecimal cost){
        assertFalse(bookValidator.isCostValid(cost.toString()));
    }

    @ParameterizedTest
    @MethodSource("provideValidDataForBook")
    void isBookValidShouldReturnTrue(String name, String imagePath, BigDecimal cost, String genre) {
        assertTrue(bookValidator.isBookValid(name, imagePath, cost.toString(), genre));

    }

    @ParameterizedTest
    @MethodSource("provideInvalidDataForBook")
    void isBookInvalidShouldReturnFalse(String name, String imagePath, BigDecimal cost,String genre) {
        assertFalse(bookValidator.isBookValid(name, imagePath, cost.toString(), genre));

    }
    public static Stream<Arguments> provideValidDataForCost() {
        return Stream.of(Arguments.of(new BigDecimal("20.2")),
                Arguments.of(new BigDecimal("1232.22")),
                Arguments.of(new BigDecimal("1")),
                Arguments.of(new BigDecimal("12323.22"))
        );
    }
    public static Stream<Arguments> provideInvalidDataForCost() {
        return Stream.of(Arguments.of(new BigDecimal("20.233")),
                Arguments.of(new BigDecimal("1232121.22")),
                Arguments.of(new BigDecimal("-12")),
                Arguments.of(new BigDecimal("1232321324.22"))
        );
    }
    public static Stream<Arguments> provideValidDataForBook() {
        return Stream.of(Arguments.of("Игра рафа","123.jpg", new BigDecimal("20.33"),"Фантастика"),
                Arguments.of("Игра2","1232ewsd3.jpg", new BigDecimal("1000.33"),"horror"),
                Arguments.of("Играsadfdasfsdafdasasdf","123.jpg", new BigDecimal("20.23"),"Проза"),
                Arguments.of("Игра2345","12sadf3.jpg", new BigDecimal("230456.3"),"Фантастика"),
                Arguments.of("Игра ра фа","123asdf.jpg", new BigDecimal("2"),"Ужасы")
        );
    }
    public static Stream<Arguments> provideInvalidDataForBook() {
        return Stream.of(Arguments.of("Игра# рафа","123s.jpg", new BigDecimal("20.33"),"Фантастика"),
                Arguments.of("Игра2","1232ewsd3.jpg", new BigDecimal("1000.335"),"horror"),
                Arguments.of("Играsadfdasfsdafdas@asdf","123.jpg", new BigDecimal("20.233"),"Проза"),
                Arguments.of("Игра2@345","12sadf3.jpg", new BigDecimal("23045633.3"),"Фантастика"),
                Arguments.of("234534252345234sdgfsdgfdsgfsdgewrgdfbfcbgfbfdbffdgewfqewfdscsadfer1313fsdsvfgdbsdaa","123asdf.jpg", new BigDecimal("234524352342"),"Ужасы")
        );
    }

}