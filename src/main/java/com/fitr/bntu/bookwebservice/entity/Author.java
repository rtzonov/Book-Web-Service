package com.fitr.bntu.bookwebservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "author")
public class Author {
    @Id
    int id;

    String name;
    @Column(name = "last_name")
    String lastName;
}
