package com.vk18.splitwise.Dtos;

import lombok.Data;

@Data
public class RegisterUserResposeDto {
    private String name;
    private String phone;
    private ResponseStatus responseStatus;
    private String reponseMessage;

}
