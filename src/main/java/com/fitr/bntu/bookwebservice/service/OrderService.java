package com.fitr.bntu.bookwebservice.service;


import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService {
    Order add(User user, Date date, String status, BigDecimal price); // может и пользователь и админ для добавления заказа

    Order updateStatus(Integer id, String status); // для админа для подтверждения заказа

    List<Order> findAll(int pageNumber, int numberOfElementsPerPage); // для админа, чтоб подтверждать или нет

    @Deprecated
    void deleteById(Integer id);// для админа при отказе тип удалить его вообще

    @Deprecated
    Order findById(Integer id); // для админа хз зачем

    List<Order> findByUserId(Integer userId);// достаю список заказов пользователя
}
