package com.vk18.splitwise.Repository;

import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Model.UserExpence;
import com.vk18.splitwise.Service.UserExpenseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpence,Long> {
    public List<UserExpence> findAllByUser(User user);

    public UserExpence save(UserExpence userExpence);

}
