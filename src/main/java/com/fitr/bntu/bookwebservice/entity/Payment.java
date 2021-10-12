package com.fitr.bntu.bookwebservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "payment")
public class Payment {
    @Id
    int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    Order order;
    Date date;

    public Payment(int id, Order order, Date date) {
        this.id = id;
        this.order = order;
        this.date = date;
    }
}
