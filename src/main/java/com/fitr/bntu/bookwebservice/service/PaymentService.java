package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.DTO.OrderDTO;
import com.fitr.bntu.bookwebservice.DTO.PaymentDTO;
import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {
    PaymentDTO add(Date date, OrderDTO order); // юзеру для оплаты

    List<PaymentDTO> findAll(int pageNumber, int numberOfElementsPerPage); // для админа посмотреть все оплачены хотя не уверен что это нужно


}
