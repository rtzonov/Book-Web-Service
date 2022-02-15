package com.fitr.bntu.bookwebservice.validator;

import com.fitr.bntu.bookwebservice.entity.User;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderValidator {
    boolean isOrderValid(String status, BigDecimal price);

    boolean isStatusValid(String status);
    boolean isPriceValid(BigDecimal price);
}
