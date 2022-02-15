package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.validator.OrderValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
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
class OrderValidatorImplTest {

    @Autowired
    private OrderValidator orderValidator;

    @ParameterizedTest
    @ValueSource(strings = {"Ready","Готово","В обработке"})
    void isStatusValidShouldReturnTrue(String status) {
        assertTrue(orderValidator.isStatusValid(status));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ",""})
    void isStatusInvalidShouldReturnFalse(String status) {
        assertFalse(orderValidator.isStatusValid(status));
    }

    @ParameterizedTest
    @CsvSource(value = {"1","100","12.99","100.99","999000.99"})
    void isPriceValidShouldReturnTrue(BigDecimal price) {
        assertTrue(orderValidator.isPriceValid(price));
    }
    @ParameterizedTest
    @CsvSource(value = {"0","23453245324","12.993","100234.992","99900012.99"})
    void isPriceInvalidShouldReturnFalse(BigDecimal price) {
        assertFalse(orderValidator.isPriceValid(price));
    }

    @ParameterizedTest
    @MethodSource("provideValidOrder")
    void isOrderValidShouldReturnTrue(String status, BigDecimal price){
        assertTrue(orderValidator.isOrderValid(status, price));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidOrder")
    void isOrderInvalidShouldReturnFalse(String status, BigDecimal price){
        assertFalse(orderValidator.isOrderValid(status, price));
    }

    private static Stream<Arguments> provideValidOrder(){
        return Stream.of(
                Arguments.of("Ready","2345.12"),
                Arguments.of("Wait","2"),
                Arguments.of("Готово","234234.15")
        );
    }
    private static Stream<Arguments> provideInvalidOrder(){
        return Stream.of(
                Arguments.of(" ","2342.123"),
                Arguments.of("","1234567"),
                Arguments.of("  ","1234567.234")
        );
    }

}