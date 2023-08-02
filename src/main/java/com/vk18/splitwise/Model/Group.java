package com.vk18.splitwise.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="Group1")
public class Group extends BaseModel{
    private String name;
    @ManyToOne
    private User admin;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
}
