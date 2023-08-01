package com.vk18.splitwise.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="Group1")
public class Group extends BaseModel{
    private String name;
    @ManyToOne
    private User admin;
    private String description;

    @ManyToMany
    private List<User> users;
}
