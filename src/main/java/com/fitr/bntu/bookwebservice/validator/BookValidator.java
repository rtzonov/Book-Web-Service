package com.fitr.bntu.bookwebservice.validator;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Genre;

import java.math.BigDecimal;

public interface BookValidator {
    boolean isBookValid(String name, String imagePath, String cost, String genre);

    boolean isNameValid(String name);

    boolean isImagePath(String imagePath);

    boolean isCostValid(String cost);

    boolean isGenreValid(String genre);
}
