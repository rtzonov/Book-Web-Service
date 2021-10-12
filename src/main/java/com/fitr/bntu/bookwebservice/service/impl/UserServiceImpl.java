package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.entity.Payment;
import com.fitr.bntu.bookwebservice.entity.User;
import com.fitr.bntu.bookwebservice.repository.UserRepository;
import com.fitr.bntu.bookwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User signIn(String login, String password) {
        User user = new User(login, password);
        return repository.save(user);
    }

    @Override
    public User signUp(String login, String password) {
        User user = new User(login, password);
        return repository.save(user);
    }

    @Override
    public List<User> findAll(int pageNumber, int numberOfElementsPerPage) {
        return (List<User>) repository.findAll(PageRequest.of(pageNumber, numberOfElementsPerPage));
    }
    // i don't write deprecated methods
}
