package com.fitr.bntu.bookwebservice.entity;

import com.fitr.bntu.bookwebservice.DTO.GenreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
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

    public Book(int id, String name, String imagePath, BigDecimal cost, Genre genre, String authorName, String authorLastName) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.cost = cost;
        this.genre = genre;
        this.author = new Author();
        this.author.setName(authorName);
        this.author.setLastName(authorLastName);
    }
}
