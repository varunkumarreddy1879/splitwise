package com.vk18.splitwise.Repository;

import com.vk18.splitwise.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    public Expense save(Expense expense);

}
