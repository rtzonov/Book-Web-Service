package com.fitr.bntu.bookwebservice.repository;

import com.fitr.bntu.bookwebservice.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {
}
