package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class CreateGroupRequestDto {
    private Long userId;
    private String groupName;
}
