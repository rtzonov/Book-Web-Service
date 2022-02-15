package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.validator.AuthorValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AuthorValidatorImpl implements AuthorValidator {

private static final Pattern PATTERN_FOR_NAME = Pattern.compile("[a-zA-Zа-яёА-ЯЁ]{2,60}");

    @Override
    public boolean isAuthorValid(Author author){
        return isNameValid(author.getName()) && isLastNameValid(author.getLastName());
    }
    @Override
    public boolean isNameValid(String name){
        return name != null && PATTERN_FOR_NAME.matcher(name).matches();
    }
    @Override
    public boolean isLastNameValid(String lastName){
        return lastName != null && PATTERN_FOR_NAME.matcher(lastName).matches();
    }
}
