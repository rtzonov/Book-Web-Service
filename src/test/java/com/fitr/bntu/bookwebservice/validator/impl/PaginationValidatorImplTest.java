package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.config.TestConfig;
import com.fitr.bntu.bookwebservice.util.Pagination;
import com.fitr.bntu.bookwebservice.validator.PaginationValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("Test")
class PaginationValidatorImplTest {
    @Autowired
    private PaginationValidator paginationValidator;

    @Test
    void isPaginationValid(){
        Pagination pagination = new Pagination(1,3);
        int amount = 2;
        assertTrue(paginationValidator.isPaginationValid(pagination, amount));
    }

    @Test
    void isPaginationInvalid(){
        Pagination pagination = new Pagination(0,0);
        int amount = 2;
        assertFalse(paginationValidator.isPaginationValid(pagination, amount));
    }
}