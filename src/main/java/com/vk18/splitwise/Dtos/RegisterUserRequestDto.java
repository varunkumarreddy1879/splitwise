package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String name;
    private String phone;
    private String password;
}
