package com.fitr.bntu.bookwebservice.DTO;

import com.fitr.bntu.bookwebservice.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    int id;
    String name;
    String imagePath;
    BigDecimal cost;
    GenreDTO genre;
    AuthorDTO author;
    Boolean isDeleted;
}
