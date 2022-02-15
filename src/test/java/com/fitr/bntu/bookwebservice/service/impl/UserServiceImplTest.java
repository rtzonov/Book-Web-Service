package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.RoleDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.entity.Role;
import com.fitr.bntu.bookwebservice.entity.User;
import com.fitr.bntu.bookwebservice.service.ServiceException;
import com.fitr.bntu.bookwebservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("Test")
class UserServiceImplTest {
    private static final RoleDTO ROLE = new RoleDTO(2,"User");

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void SignInPositive() {
        UserDTO expected = new UserDTO( 3,"login1", "$2a$12$uXSrVV5yG5JBZtE/73//4OCD/Hh5B1n4/ESEw82EpqFJH2EUaQqKO", ROLE);
        UserDTO actual = userService.signIn("login1", "12345678qwe");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDataForLogin")
    void SighInNegative(String login, String password) {
        assertThrows(ServiceException.class, ()-> userService.signIn(login, password));
    }
    @DirtiesContext
    @Test
    void signUpPositive() {
        UserDTO expected = new UserDTO(0, "login3", "$2a$12$g3nfncR6RrXVnuI.8Ne9juFOK.TSytai6coDR/xjhyxQyDfKasp0K", ROLE);
        UserDTO actual = userService.signUp("login3", "123456789йцу");
        assertEquals(expected.getLogin(),actual.getLogin());
        assertTrue(passwordEncoder.matches("123456789йцу", actual.getPassword()));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDataForRegister")
    void registerNegative(String login, String password) {
        assertThrows(ServiceException.class, ()-> userService.signUp(login, password));
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDataForFindAll")
    void findAllNegative(int page, int amount) {
        assertThrows(ServiceException.class, ()-> userService.findAll(page, amount));
    }


    public static Stream<Arguments> provideInvalidDataForFindAll(){
        return Stream.of(
                Arguments.of( 0, 1),
                Arguments.of( 1, 0)
        );
    }

    @Test
    void findAllPositive() {
        UserDTO user1 = new UserDTO(3,"login1", "$2a$12$uXSrVV5yG5JBZtE/73//4OCD/Hh5B1n4/ESEw82EpqFJH2EUaQqKO", ROLE);
        UserDTO user2 = new UserDTO(4, "login2", "$2a$12$4ObQNd/Hg3RQorYA9ZYYWOg9VyUZ6YtvmHgSUeK1a8mxXTjG3.JAu", ROLE);
        List<UserDTO> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);
        List<UserDTO> actual = userService.findAll(1, 20);
        assertIterableEquals(expected, actual);
    }

    private static Stream<Arguments> provideInvalidDataForLogin() {
    return Stream.of(
            Arguments.of("/.gt", "password"),
            Arguments.of("goodlogin", "bad"),
            Arguments.of("loginNotInBd", "password"),
            Arguments.of("login1", "passwordwrong")
    );
    }
    private static Stream<Arguments> provideInvalidDataForRegister() {
        return Stream.of(
                Arguments.of( "login", "password 32"),
                Arguments.of("login invalid", "password#"),
                Arguments.of("login", "password@"),
                Arguments.of("login", "pass&"),
                Arguments.of("login", "password?"),
                Arguments.of("login1", "password  ")
        );
    }
}
