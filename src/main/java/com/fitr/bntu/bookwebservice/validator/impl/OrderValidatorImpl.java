package com.fitr.bntu.bookwebservice.validator.impl;

import com.fitr.bntu.bookwebservice.entity.User;
import com.fitr.bntu.bookwebservice.validator.OrderValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class OrderValidatorImpl implements OrderValidator {

    private static final Pattern PATTERN_FOR_PRICE = Pattern.compile("[\\d]{1,6}(\\.\\d{2})?");

    @Override
    public boolean isOrderValid(String status, BigDecimal price){
        return isPriceValid(price) && isStatusValid(status);
    }
    @Override
    public boolean isStatusValid(String status){
        return status != null && !status.equals("") && !status.equals(" ");
    }
    @Override
    public boolean isPriceValid(BigDecimal price){
        return price != null && PATTERN_FOR_PRICE.matcher(price.toString()).matches() && !price.equals(new BigDecimal("0"));
    }

}
