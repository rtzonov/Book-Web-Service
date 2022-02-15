package com.fitr.bntu.bookwebservice.data;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@Data
public class BookData {
    private Integer id;
    private String name;
    private BigDecimal cost;
    private String genre;
    private String author;
    private MultipartFile imageFile;

}
