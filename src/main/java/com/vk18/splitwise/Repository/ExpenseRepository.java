package com.vk18.splitwise.Repository;

import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    public Expense save(Expense expense);

    public List<Expense> findAllByGroup(Group group);
}
