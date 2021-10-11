package com.fitr.bntu.bookwebservice.service;


import com.fitr.bntu.bookwebservice.entity.Role;

import java.util.List;

@Deprecated
public interface RoleService {// мб это просто енам

    Role add(String userType);// юзлез

    Role edit(Integer id, String userType);// юзлез

    List<Role> findAll(int pageNumber, int numberOfElementsPerPage);// админу по рофлу

    void deleteById(Integer id);// думаю не надо этого делать

    Role findById(Integer id);// не надо
}
