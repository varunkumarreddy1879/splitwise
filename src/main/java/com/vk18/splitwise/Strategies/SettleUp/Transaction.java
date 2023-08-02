package com.vk18.splitwise.Strategies.SettleUp;

import com.vk18.splitwise.Model.BaseModel;
import com.vk18.splitwise.Model.User;
import lombok.Data;

@Data
public class Transaction {
    private User from;
    private User to;
    private int amount;
}
