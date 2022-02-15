package com.fitr.bntu.bookwebservice.validator;

public interface UserValidator {
    boolean isUserValid(String login, String password);
    boolean isLoginValid(String login);
    boolean isPasswordValid(String password);
}
