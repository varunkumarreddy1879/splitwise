package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class AddUserResponseDto {
    private String adminName;
    private String groupName;
    private String UserName;
    private ResponseStatus responseStatus;
    private String responseMessage;
}
