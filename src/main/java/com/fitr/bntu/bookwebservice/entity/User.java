package com.fitr.bntu.bookwebservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    int id;
    String login;
    String password;
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "roles_id")
    Role role;

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
