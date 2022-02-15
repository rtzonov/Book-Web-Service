package com.fitr.bntu.bookwebservice.validator;

import com.fitr.bntu.bookwebservice.util.Pagination;

public interface PaginationValidator {

    boolean isPaginationValid(Pagination pagination, int amount);
}
