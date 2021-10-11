package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {
    Payment add(Date date, Order order); // юзеру для оплаты

    @Deprecated
    Payment edit(Integer id, Date date, Order order); // мб для админа тип подтвердить что оплатил

    List<Payment> findAll(int pageNumber, int numberOfElementsPerPage); // для админа посмотреть все оплачены хотя не уверен что это нужно

    @Deprecated
    void deleteById(Integer id);// для админа удалить из списка , не знаю зачем

    @Deprecated
    Payment findById(Integer id);//админу ну зачем
}
