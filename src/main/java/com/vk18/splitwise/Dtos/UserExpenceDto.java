package com.vk18.splitwise.Dtos;

import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Model.UserExpenseType;
import lombok.Data;

@Data
public class UserExpenceDto {
    private String userName;
    private String description;
    private String groupName;
    private String createdBy;
    private String expenseType;
    private int totalAmount;
    private String userExpenseType;
    private int amount;
}
