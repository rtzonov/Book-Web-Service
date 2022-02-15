package com.fitr.bntu.bookwebservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
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

}
