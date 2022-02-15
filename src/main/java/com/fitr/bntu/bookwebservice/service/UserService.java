package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.entity.User;

import java.util.List;

public interface UserService {
    UserDTO signIn(String login, String password);// аторизоваться польззователю

    UserDTO signUp(String login, String password);// зарегаться пользователю

    List<UserDTO> findAll(int pageNumber, int numberOfElementsPerPage);// посмотреть весь список пользователя для админа
}
