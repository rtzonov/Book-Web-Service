package com.fitr.bntu.bookwebservice.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {

    int id;
    OrderDTO order;
    Date date;
}
