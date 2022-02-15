package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.util.Pagination;
import com.fitr.bntu.bookwebservice.validator.PaginationValidator;
import org.springframework.stereotype.Component;

@Component
public class PaginationValidatorImpl implements PaginationValidator {

    @Override
    public boolean isPaginationValid(Pagination pagination, int amount){
        return pagination != null &&
                pagination.getNumberOfElementsPerPage() > 0 &&
                pagination.getPageNumber() > 0 &&
                pagination.getPageNumber() <= Math.ceil(((double) amount / pagination.getNumberOfElementsPerPage()));
    }
}
