package com.vk18.splitwise.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Expense extends BaseModel{
    @ManyToOne
    private User createdBy;
    private String description;

    @ManyToOne
    private Group group;
    private int amount;
    @Enumerated(EnumType.ORDINAL)
    private ExpenceType expenceType;
}
