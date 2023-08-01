package com.vk18.splitwise.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserGroupsResponseDto {
    private String userName;
    private ResponseStatus responseStatus;
    private String responseMessage;
    private List<String> groups;
}
