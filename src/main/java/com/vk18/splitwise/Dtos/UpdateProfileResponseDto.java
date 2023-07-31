package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class UpdateProfileResponseDto {
    private String name;
    private ResponseStatus responseStatus;
    private String responseMessage;
}
