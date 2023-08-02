package com.vk18.splitwise.Dtos;

import com.vk18.splitwise.Strategies.SettleUp.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class GroupSettleUpResponseDto {
    private ResponseStatus responseStatus;
    private String responseMessage;
    List<Transaction> transactions;
}
