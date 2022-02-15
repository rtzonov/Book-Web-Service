package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.validator.UserValidator;
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
class UserValidatorImplTest {

    @Autowired
    private UserValidator userValidator;

    @ParameterizedTest
    @ValueSource(strings = {"onov",
            "login123",
            "ывпвапппцу234",
            "123sqыфФЫВваr",
            "qwertyuiop1234567891"})
    void isLoginValidShouldReturnTrue(String login){
        assertTrue(userValidator.isLoginValid(login));
    }
    @ParameterizedTest
    @ValueSource(strings = {"ono",
            "login 123",
            "logindfФЫВ dsfg123",
            "ывпваыe2 34r",
            "qwertyuiop1234567891s"})
    void isLoginInvalidShouldReturnFalse(String login){
        assertFalse(userValidator.isLoginValid(login));
    }
    @ParameterizedTest
    @ValueSource(strings = {"ono2345s",
            "loginвапМФЫВ123",
            "logindfdsfg123",
            "ывпваыe234r",
            "qwertyuiop1234567891"})
    void isPasswordValidShouldReturnTrue(String password){
        assertTrue(userValidator.isLoginValid(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ono23 45",
            "loginвапМ ФЫВ123",
            "logind fdsfg1#23",
            "ывпв2@13 аыe234r",
            "qwertyuiop1234567891S"})
    void isPasswordValidShouldReturnFalse(String password){
        assertFalse(userValidator.isLoginValid(password));
    }

    @ParameterizedTest
    @MethodSource("provideValidUser")
    void isUserValidShouldReturnTrue(String login, String password) {
        assertTrue(userValidator.isUserValid(login, password));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidUser")
    void isUserInvalidShouldReturnFalse(String login, String password) {
        assertFalse(userValidator.isUserValid(login, password));
    }
    private static Stream<Arguments> provideValidUser(){
        return Stream.of(
                Arguments.of("Джек21","2345asйr"),
                Arguments.of("Lordsss","SADF21324"),
                Arguments.of("sdgfsdf","xcv234bd"),
                Arguments.of("Джut","1234567890qwertyuiop"),
                Arguments.of("Дж21еasdк","ВороASDбеasdfй")
        );
    }
    private static Stream<Arguments> provideInvalidUser(){
        return Stream.of(
                Arguments.of("Дas","Вasd$#й"),
                Arguments.of("Losaw rd","gsdaASDFEWE!@"),
                Arguments.of("sdgf s2345df","xcv23SA 45bd"),
                Arguments.of("Дж","qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopsdgd"),
                Arguments.of("ДжеasSAdк j","Воробеa%32452341sdfй")
        );
    }
}