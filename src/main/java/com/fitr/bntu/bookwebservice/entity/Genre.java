package com.fitr.bntu.bookwebservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "genre")
public class Genre {
    @Id
    int id;

    String type;

}
