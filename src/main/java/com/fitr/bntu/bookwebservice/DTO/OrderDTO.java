package com.fitr.bntu.bookwebservice.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {

    int id;
    UserDTO user;
    Date date;
    BigDecimal price;
}
