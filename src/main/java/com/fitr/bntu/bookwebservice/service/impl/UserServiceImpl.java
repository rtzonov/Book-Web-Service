package com.fitr.bntu.bookwebservice.service.impl;

import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.entity.Role;
import com.fitr.bntu.bookwebservice.entity.User;
import com.fitr.bntu.bookwebservice.repository.UserRepository;
import com.fitr.bntu.bookwebservice.service.ServiceException;
import com.fitr.bntu.bookwebservice.service.UserService;
import com.fitr.bntu.bookwebservice.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Role ROLE = new Role(2,"User");

    private final UserValidator userValidator;

    private final UserRepository repository;

    private final ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserValidator userValidator, UserRepository repository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userValidator = userValidator;
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO signIn(String login, String password) {
        User user = repository.findByLogin(login)
                .orElseThrow(() -> new ServiceException("User with this login or password is not correct"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ServiceException("User with this login or password is not correct");
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO signUp(String login, String password) {
        if (!userValidator.isUserValid(login,password)){
            throw new ServiceException("Invalid data in fields login or password");
        }
        User user = new User(login, passwordEncoder.encode(password), ROLE);
        return convertToDTO(repository.save(user));
    }

    @Override
    public List<UserDTO> findAll(int pageNumber, int numberOfElementsPerPage) {
        if (pageNumber < 1 || numberOfElementsPerPage < 1) {
            throw new ServiceException("Invalid page number");
        }
        return repository.findAll(PageRequest.of(pageNumber - 1, numberOfElementsPerPage)).getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO convertToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
}
