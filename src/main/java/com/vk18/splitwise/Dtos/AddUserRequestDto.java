package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class AddUserRequestDto {
    private Long adminId;
    private Long groupId;
    private Long userId;
}
