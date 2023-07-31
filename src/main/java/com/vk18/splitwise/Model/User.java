package com.vk18.splitwise.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User extends BaseModel{
    private String name;
    private String phone;
    private String password;
    @ManyToMany
    private List<Group> groups;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;
}
