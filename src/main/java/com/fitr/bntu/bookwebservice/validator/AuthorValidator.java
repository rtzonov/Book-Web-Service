package com.fitr.bntu.bookwebservice.validator;

import com.fitr.bntu.bookwebservice.entity.Author;

public interface AuthorValidator {
    boolean isAuthorValid(Author author);
    boolean isNameValid(String name);
    boolean isLastNameValid(String lastName);
    boolean isAuthorForCreateValid(String name, String lastname);

}
