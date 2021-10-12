package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.User;
import com.fitr.bntu.bookwebservice.repository.OrderRepository;
import com.fitr.bntu.bookwebservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order add(User user, Date date, String status, BigDecimal price) {
        Order order = new Order(0, user, date, price);
        return repository.save(order);
    }

    @Override
    public Order updateStatus(Integer id, String status) {
        Order order = new Order(id, status);
        return repository.save(order);
    }

    @Override
    public List<Order> findAll(int pageNumber, int numberOfElementsPerPage) {
        return (List<Order>) repository.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage));
    }

    @Override
    //make custom method and think about it logic
    public List<Order> findByUserId(Integer userId) {
        return
    }
    // i don't write deprecated methods
}
