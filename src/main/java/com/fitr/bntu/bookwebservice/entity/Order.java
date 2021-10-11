package com.fitr.bntu.bookwebservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "order")
public class Order {
    @Id
    int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;
    Date date;
    BigDecimal price;
}
