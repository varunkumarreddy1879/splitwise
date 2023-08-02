package com.vk18.splitwise.Strategies.SettleUp;

import com.vk18.splitwise.Model.Expense;

import java.util.List;

public interface SettleUpStrategy {
    public List<Transaction> Settle(List<Expense> expenses);
}
