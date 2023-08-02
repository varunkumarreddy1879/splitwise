package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class GroupExpenseEqualShareRequestDto {
    private Long userId;
    private Long groupId;
    private int amount;
    private String description;
}
