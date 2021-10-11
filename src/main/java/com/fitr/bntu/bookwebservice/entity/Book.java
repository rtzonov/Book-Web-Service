package com.fitr.bntu.bookwebservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    int id;

    String name;
    @Column(name = "image_path")
    String imagePath;

    BigDecimal cost;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    Author author;


}
