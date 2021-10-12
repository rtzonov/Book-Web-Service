package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.Payment;
import com.fitr.bntu.bookwebservice.repository.PaymentRepository;
import com.fitr.bntu.bookwebservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment add(Date date, Order order) {
        Payment payment = new Payment(0, order, date);
        return repository.save(payment);
    }

    @Override
    public List<Payment> findAll(int pageNumber, int numberOfElementsPerPage) {
        return (List<Payment>) repository.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage));
    }
    // i don't write deprecated methods
}
