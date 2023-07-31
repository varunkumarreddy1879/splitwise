package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class UpdateProfileRequestDto {
    private String phone;
    private String newPassword;
}
