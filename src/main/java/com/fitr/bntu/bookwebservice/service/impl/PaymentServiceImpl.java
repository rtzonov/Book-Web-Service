package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.OrderDTO;
import com.fitr.bntu.bookwebservice.DTO.PaymentDTO;
import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.Payment;
import com.fitr.bntu.bookwebservice.repository.OrderRepository;
import com.fitr.bntu.bookwebservice.repository.PaymentRepository;
import com.fitr.bntu.bookwebservice.service.PaymentService;
import com.fitr.bntu.bookwebservice.service.ServiceException;
import com.fitr.bntu.bookwebservice.validator.OrderValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    private final ModelMapper mapper;

    private final OrderRepository repositoryOrder;

    private final OrderValidator orderValidator;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository, ModelMapper mapper, OrderRepository repositoryOrder, OrderValidator orderValidator) {
        this.repository = repository;
        this.mapper = mapper;
        this.repositoryOrder = repositoryOrder;
        this.orderValidator = orderValidator;
    }

    @Override
    public PaymentDTO add(Date date, OrderDTO order) {
        Order order1 = repositoryOrder.findById(order.getId()).orElseThrow(() -> new ServiceException("Order is not exist"));
        orderValidator.isOrderValid(order1.getStatus() ,order1.getPrice());
        Payment payment = new Payment(0, order1, date);
        return convertToDTO( repository.save(payment));
    }

    @Override
    public List<PaymentDTO> findAll(int pageNumber, int numberOfElementsPerPage) {
        return repository.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public PaymentDTO convertToDTO(Payment payment) {
        return mapper.map(payment, PaymentDTO.class);
    }
}
