package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class UpdateProfileRequestDto {
    private Long userId;
    private String newPassword;
}
