package com.demo.spring_security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String roles;
}
