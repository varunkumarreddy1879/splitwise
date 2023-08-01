package com.vk18.splitwise.Dtos;

import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.UserExpence;
import com.vk18.splitwise.Model.UserExpenseType;
import lombok.Data;

import java.util.List;
@Data
public class UserHistoryResponseDto {
    private ResponseStatus responseStatus;
    private String responseMessage;

    private List<UserExpenceDto> UserExpenses;
}
