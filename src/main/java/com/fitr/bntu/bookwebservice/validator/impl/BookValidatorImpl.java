package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Genre;
import com.fitr.bntu.bookwebservice.validator.BookValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class BookValidatorImpl implements BookValidator {

    private static final Pattern PATTERN_FOR_NAME = Pattern.compile("[a-zA-Zа-яёА-ЯЁ\\d\\s]{2,60}");
    private static final Pattern PATTERN_FOR_COST = Pattern.compile("[\\d]{1,6}(\\.\\d{1,2})?");

    @Override
    public boolean isBookValid(String name, String imagePath, String cost, String genre) {
        return isImagePath(imagePath) && isCostValid(cost) && isGenreValid(genre) && isNameValid(name);
    }

    @Override
    public boolean isNameValid(String name) {
        return (name != null && PATTERN_FOR_NAME.matcher(name).matches());
    }

    @Override
    public boolean isImagePath(String imagePath) {
        return imagePath != null;
    }

    @Override
    public boolean isCostValid(String cost) {
        return cost != null && PATTERN_FOR_COST.matcher(cost).matches();
    }

    @Override
    public boolean isGenreValid(String genre) {
        return genre != null;
    }


}
