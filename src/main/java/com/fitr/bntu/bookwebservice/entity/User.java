package com.fitr.bntu.bookwebservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    int id;
    String login;
    String password;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id")
    Role role;

}
