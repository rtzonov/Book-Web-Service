package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.OrderDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.User;
import com.fitr.bntu.bookwebservice.repository.OrderRepository;
import com.fitr.bntu.bookwebservice.repository.UserRepository;
import com.fitr.bntu.bookwebservice.service.OrderService;
import com.fitr.bntu.bookwebservice.service.ServiceException;
import com.fitr.bntu.bookwebservice.validator.OrderValidator;
import com.fitr.bntu.bookwebservice.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserValidator userValidator;

    private final OrderRepository repository;

    private final ModelMapper mapper;

    private final UserRepository repositoryUser;

    private final OrderValidator orderValidator;

    @Autowired
    public OrderServiceImpl(UserValidator userValidator, OrderRepository repository, ModelMapper mapper, UserRepository repositoryUser, OrderValidator orderValidator) {
        this.userValidator = userValidator;
        this.repository = repository;
        this.mapper = mapper;
        this.repositoryUser = repositoryUser;
        this.orderValidator = orderValidator;
    }

    @Override
    public OrderDTO add(UserDTO user, Date date, String status, BigDecimal price) {
        User user1 = repositoryUser.findById(user.getId()).orElseThrow(() -> new ServiceException("User is not exist"));
        Order order = new Order(0, user1, date, price, status);
        userValidator.isUserValid(user1.getLogin(), user1.getPassword());
        orderValidator.isOrderValid(status,price);
        return convertToDTO(repository.save(order));
    }

    @Override
    public OrderDTO updateStatus(Integer id, String status) {
        Order order = new Order(id, status);
        orderValidator.isStatusValid(status);
        return convertToDTO(repository.save(order));
    }

    @Override
    public List<OrderDTO> findAll(int pageNumber, int numberOfElementsPerPage) {
        return repository.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByUserId(Integer userId) {
        User user = repositoryUser.findById(userId).orElseThrow(() -> new ServiceException("User is not exist"));
        return repository.findByUser(user)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO convertToDTO(Order order) {
        return mapper.map(order, OrderDTO.class);
    }

}
