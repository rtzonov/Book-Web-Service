package com.fitr.bntu.bookwebservice.service;

import com.fitr.bntu.bookwebservice.entity.User;

import java.util.List;

public interface UserService {
    User signIn(String login, String password);// аторизоваться польззователю

    User signUp(String login, String password);// зарегаться пользователю

    @Deprecated
    User findById(Integer id);// админу глянуть на пользователя но зачем?

    List<User> findAll(int pageNumber, int numberOfElementsPerPage);// посмотреть весь список пользователя для админа
}
