package com.fitr.bntu.bookwebservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    int id;
    @Column(name = "users_type")
    String role;
}
