package com.vk18.splitwise.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserExpence extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    @Enumerated(EnumType.ORDINAL)
    private UserExpenseType userExpenseType;
    private int amount;
}
