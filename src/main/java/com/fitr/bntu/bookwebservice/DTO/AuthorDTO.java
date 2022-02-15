package com.fitr.bntu.bookwebservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDTO {

    int id;
    String name;
    String lastName;

    @Override
    public String toString() {
        return name + " " + lastName;
    }
}
