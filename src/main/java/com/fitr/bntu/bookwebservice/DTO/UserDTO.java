package com.fitr.bntu.bookwebservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    int id;
    String login;
    String password;
    RoleDTO role;
}
