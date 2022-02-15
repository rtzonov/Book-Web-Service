package com.fitr.bntu.bookwebservice.data;

import com.fitr.bntu.bookwebservice.entity.Author;
import com.fitr.bntu.bookwebservice.entity.Genre;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class BookDataForSearch {
    private int id;
    private String name;
    private BigDecimal cost;
    private Genre genre;
    private Author author;

}
