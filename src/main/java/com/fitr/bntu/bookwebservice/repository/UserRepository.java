package com.fitr.bntu.bookwebservice.repository;

import com.fitr.bntu.bookwebservice.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
