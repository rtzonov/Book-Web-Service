package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.validator.UserValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class UserValidatorImpl implements UserValidator {

    private static final Pattern PATTERN_FOR_LOGIN = Pattern.compile("[a-zA-Zа-яёА-ЯЁ\\d]{4,20}");
    private static final Pattern PATTERN_FOR_PASSWORD = Pattern.compile("[a-zA-Zа-яёА-ЯЁ\\d]{8,20}");

    @Override
    public boolean isUserValid(String login, String password) {
        return isLoginValid(login) && isPasswordValid(password);
    }
    @Override
    public boolean isLoginValid(String login){
        return (login != null && PATTERN_FOR_LOGIN.matcher(login).matches());
    }
    @Override
    public boolean isPasswordValid(String password){
        return (password != null && PATTERN_FOR_PASSWORD.matcher(password).matches());
    }
}
