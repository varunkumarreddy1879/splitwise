package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class CreateGroupResponseDto {
    private String userName;
    private String groupName;
    private ResponseStatus responseStatus;
    private String responseMessage;
}
