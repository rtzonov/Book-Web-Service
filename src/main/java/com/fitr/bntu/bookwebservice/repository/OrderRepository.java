package com.fitr.bntu.bookwebservice.repository;

import com.fitr.bntu.bookwebservice.entity.Order;
import com.fitr.bntu.bookwebservice.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

    List<Order> findByUser(User user);
}
