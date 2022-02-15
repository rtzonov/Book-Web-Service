package com.fitr.bntu.bookwebservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDTO {

    int id;
    String type;

    @Override
    public String toString() {
        return type.toLowerCase();
    }
}
