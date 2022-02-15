package com.fitr.bntu.bookwebservice.service;


import com.fitr.bntu.bookwebservice.DTO.OrderDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService {
    OrderDTO add(UserDTO user, Date date, String status, BigDecimal price); // может и пользователь и админ для добавления заказа

    OrderDTO updateStatus(Integer id, String status); // для админа для подтверждения заказа

    List<OrderDTO> findAll(int pageNumber, int numberOfElementsPerPage); // для админа, чтоб подтверждать или нет
    List<OrderDTO> findByUserId(Integer userId);// достаю список заказов пользователя
}
